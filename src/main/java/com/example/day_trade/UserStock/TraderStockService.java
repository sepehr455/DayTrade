package com.example.day_trade.UserStock;

import com.example.day_trade.Stock.Stock;
import com.example.day_trade.Stock.StockService;
import com.example.day_trade.Trader.Trader;
import com.example.day_trade.Trader.TraderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@SuppressWarnings("DuplicatedCode")
@Service
public class TraderStockService {

    private final TraderStockRepository traderStockRepository;
    private final StockService stockService;
    private final TraderService traderService;

    public TraderStockService(TraderStockRepository userStockRepository, StockService stockService, TraderService traderService) {
        this.traderStockRepository = userStockRepository;
        this.stockService = stockService;
        this.traderService = traderService;
    }

    public TraderStockDto userStockToUserStockDtoConverter(TraderStock traderStock) {
        return new TraderStockDto(traderStock.getTrader(), traderStock.getStock(), traderStock.getQuantity());
    }

    public void buyStock(Long userId, String stockName, int quantity) {
        Long currentStockId = stockService.getStockId(stockName);

        // Getting the current trader and the stock
        Trader currentTrader = traderService.getTraderById(userId)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Unable to find the user with the given id"));
        Stock currentStock = stockService.getStockById(currentStockId)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Unable to find the stock with the given id"));

        // Getting the total cost for the amount of shares to be purchased
        int totalCost = currentStock.getStockPrice() * quantity;
        int userBalance = currentTrader.getCurrentBalance();

        // Checking if the user has enough money
        if (userBalance < totalCost) {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The user does not have enough money to buy the stock ");
        } else {
            TraderStock traderStock = traderStockRepository.findByTraderUserIdAndStockStockId(userId, currentStockId)
                    .orElse(new TraderStock(currentTrader, currentStock, 0));
            traderStock.quantity += quantity;
            traderStockRepository.save(traderStock);
            traderService.subtractBalance(currentTrader.userId, totalCost);
        }
        ResponseEntity.status(HttpStatus.OK).body("Successfully bought the stock shares");
    }

    public void sellStocks(Long userId, String stockName, int quantity) {
        Long currentStockId = stockService.getStockId(stockName);

        // Getting the current trader and the stock
        Trader currentTrader = traderService.getTraderById(userId)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Unable to find the user with the given id"));
        Stock currentStock = stockService.getStockById(currentStockId)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Unable to find the stock with the given id"));

        // Getting the total cost for the amount of shares to be purchased
        int totalCost = currentStock.getStockPrice() * quantity;

        TraderStock traderStock = traderStockRepository.findByTraderUserIdAndStockStockId(userId, currentStockId)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "The user does not own any shares of the stock"));
        traderStock.quantity -= quantity;
        traderStockRepository.save(traderStock);

        if (traderStock.quantity == 0){
            traderStockRepository.deleteAllById(Collections.singleton(traderStock.getId()));
        }

        traderService.addBalance(currentTrader.userId, totalCost);
        ResponseEntity.status(HttpStatus.OK).body("Successfully bought the stock shares");
    }

}
