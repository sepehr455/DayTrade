package com.example.day_trade.UserStock;

import com.example.day_trade.Stock.Stock;
import com.example.day_trade.Stock.StockService;
import com.example.day_trade.Trader.Trader;
import com.example.day_trade.Trader.TraderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
public class TraderStockController {
    private final TraderService traderService;
    private final TraderStockService traderStockService;
    private final StockService stockService;

    public TraderStockController(TraderService userService, TraderStockService userStockService, StockService stockService) {
        this.traderService = userService;
        this.traderStockService = userStockService;
        this.stockService = stockService;
    }

    @GetMapping("/user/{userId}/holding")
    public List<TraderStockDto> getUserHolding(@PathVariable Long userId) {
        return traderService.getUserHoldings(userId)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Unable to find the user with the given id"))
                .stream()
                .map(traderStockService::userStockToUserStockDtoConverter).toList();
    }

    @PostMapping("/user/{userId}/holding/{stockName}/buy")
    public ResponseEntity<TraderStockDto> buyStock(@PathVariable String stockName, @PathVariable Long userId, @RequestBody String quantity) {

        Trader trader = traderService.getTraderById(userId)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Unable to find the user with the given id"));
        Stock stock = stockService.getStockByName(stockName)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Unable to find the stock with the given name"));

        int stockQuantity = Integer.parseInt(quantity.strip());
        boolean purchaseStatus = traderStockService.buyStock(trader, stock, stockQuantity);
        Optional<TraderStock> traderStock = traderStockService.getTraderStock(trader.getUserId(), stockName);

        Optional<TraderStockDto> traderStockDto = traderStock.map(traderStockService::userStockToUserStockDtoConverter);
        if (purchaseStatus) {
            return traderStockDto
                    .map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PostMapping("user/{userId}/holding/{stockName}/sell")
    public ResponseEntity<TraderStockDto> sellStocks(@PathVariable String stockName, @PathVariable Long userId, @RequestBody String quantity) {
        int stockQuantity = Integer.parseInt(quantity.strip());

        TraderStock traderStock = traderStockService.getTraderStock(userId, stockName)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Unable to find the traderStock"));

        boolean sellStatus = traderStockService.sellStocks(traderStock, stockQuantity);

        TraderStockDto traderStockDto = traderStockService.userStockToUserStockDtoConverter(traderStock);

        if (sellStatus) {
            return ResponseEntity.ok(traderStockDto);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}




