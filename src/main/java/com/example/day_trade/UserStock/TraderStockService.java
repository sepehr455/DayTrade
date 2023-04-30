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

    public void updateQuantity(int quantity, TraderStock traderStock, int sign) {
        traderStock.quantity += (sign * quantity);
        traderStockRepository.save(traderStock);
    }

    public TraderStock getTraderStock(Long userId, String stockName) {
        Long currentStockId = stockService.getStockId(stockName);

        Trader currentTrader = traderService.getTraderById(userId)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Unable to find the user with the given id"));
        Stock currentStock = stockService.getStockById(currentStockId)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Unable to find the stock with the given id"));

        return traderStockRepository.findByTraderUserIdAndStockStockId(userId, currentStockId)
                .orElse(new TraderStock(currentTrader, currentStock, 0));
    }

    public void buyStock(Long userId, String stockName, int quantity) {
        TraderStock traderStock = getTraderStock(userId, stockName);

        int totalCost = traderStock.getStock().getStockPrice() * quantity;
        int userBalance = traderStock.getTrader().getCurrentBalance();

        if (userBalance < totalCost) {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The user does not have enough money to buy the stock ");
        } else {
            updateQuantity(quantity, traderStock, 1);
            traderService.subtractBalance(traderStock.getTrader().getUserId(), totalCost);
        }

        ResponseEntity.status(HttpStatus.OK).body("Successfully bought the stock shares");
    }


    public void sellStocks(Long userId, String stockName, int quantity) {
        TraderStock traderStock = getTraderStock(userId, stockName);
        updateQuantity(quantity, traderStock, -1);

        if (traderStock.getQuantity() == 0){
            traderStockRepository.deleteAllById(Collections.singleton(traderStock.getId()));
        }

        int totalCost = traderStock.getStock().getStockPrice() * quantity;
        traderService.addBalance(traderStock.getTrader().getUserId(), totalCost);
        ResponseEntity.status(HttpStatus.OK).body("Successfully bought the stock shares");
    }

}
