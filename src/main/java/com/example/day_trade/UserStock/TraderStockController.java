package com.example.day_trade.UserStock;

import com.example.day_trade.Stock.Stock;
import com.example.day_trade.Stock.StockDto;
import com.example.day_trade.Stock.StockService;
import com.example.day_trade.Trader.TraderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

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

    //  Endpoint for getting a user's holding
    @GetMapping("/user/{userId}/holding")
    public List<TraderStockDto> getUserHolding(@PathVariable Long userId) {
        return traderService.getUserHoldings(userId)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Unable to find the user with the given id"))
                .stream()
                .map(traderStockService::userStockToUserStockDtoConverter).toList();
    }



    // Endpoint for getting info about the stock
    @GetMapping("/stock/{stockName}")
    public ResponseEntity<StockDto> getStockInfo(@PathVariable String stockName){
        Stock stock = stockService.getStockDetails(stockName)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "No stock found with the given name"));
        StockDto stockDto = stockService.stockToStockDtoConverter(stock);
        return ResponseEntity.ok(stockDto);
    }

    // Endpoint for buying stocks
    @PostMapping("user/{userId}/holding/{stockName}/buy")
    public void buyStock(@PathVariable String stockName, @PathVariable Long userId, @RequestBody String quantity){
        int stockQuantity = Integer.parseInt(quantity.strip());
        traderStockService.buyStock(userId, stockName, stockQuantity);
    }

    // Endpoint for selling stocks
    @PostMapping("user/{userId}/holding/{stockName}/sell")
    public void sellStocks(@PathVariable String stockName, @PathVariable Long userId, @RequestBody String quantity){
        int stockQuantity = Integer.parseInt(quantity.strip());
        traderStockService.sellStocks(userId, stockName, stockQuantity);
    }
}




