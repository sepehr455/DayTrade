package com.example.day_trade.UserStock;

import com.example.day_trade.Trader.TraderService;
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

    public TraderStockController(TraderService userService, TraderStockService userStockService) {
        this.traderService = userService;
        this.traderStockService = userStockService;
    }

    @GetMapping("/user/{userId}/holding")
    public List<TraderStockDto> getUserHolding(@PathVariable Long userId) {
        return traderService.getUserHoldings(userId)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Unable to find the user with the given id"))
                .stream()
                .map(traderStockService::userStockToUserStockDtoConverter).toList();
    }

    @PostMapping("user/{userId}/holding/{stockName}/buy")
    public void buyStock(@PathVariable String stockName, @PathVariable Long userId, @RequestBody String quantity) {
        int stockQuantity = Integer.parseInt(quantity.strip());
        traderStockService.buyStock(userId, stockName, stockQuantity);
    }

    @PostMapping("user/{userId}/holding/{stockName}/sell")
    public void sellStocks(@PathVariable String stockName, @PathVariable Long userId, @RequestBody String quantity) {
        int stockQuantity = Integer.parseInt(quantity.strip());
        traderStockService.sellStocks(userId, stockName, stockQuantity);
    }
}




