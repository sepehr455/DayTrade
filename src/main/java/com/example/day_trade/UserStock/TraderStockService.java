package com.example.day_trade.UserStock;

import com.example.day_trade.Stock.Stock;
import com.example.day_trade.Stock.StockService;
import com.example.day_trade.Trader.Trader;
import com.example.day_trade.Trader.TraderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class TraderStockService {

    private final TraderStockRepository traderStockRepository;
    private final StockService stockService;
    private final TraderService traderService;

    public TraderStockService(TraderStockRepository userStockRepository, StockService stockService, TraderService traderService){
        this.traderStockRepository = userStockRepository;
        this.stockService = stockService;
        this.traderService = traderService;
    }

    public TraderStockDto userStockToUserStockDtoConverter (TraderStock traderStock){
        return new TraderStockDto(traderStock.getTrader(), traderStock.getStock(), traderStock.getQuantity());
    }

    public void buyStock(Long userId, String stockName, int quantity){
        Long currentStockId = stockService.getStockId(stockName);

        Trader currentTrader = traderService.getTraderById(userId)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Unable to find the user with the given id"));

        Stock currentStock = stockService.getStockById(currentStockId)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Unable to find the stock with the given id"));

        // Getting the total cost for the amount of shares to be purchased
        int totalCost = currentStock.getStockPrice() * quantity;

        int userBalance = currentTrader.getCurrentBalance();

        // Checking if the user has enough money
        if (userBalance < totalCost){
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The user does not have enough money to buy the stock ");
        }else{
            // Case 1: the user already has a share of the stock
            if(traderStockRepository.existsByTraderUserIdAndStockStockId(currentStockId, userId)){
                TraderStock traderStock = traderStockRepository.findByTraderUserIdAndStockStockId(userId, currentStockId)
                        .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "No combination of user and stock was found"));
                traderStock.quantity += quantity;
                traderStockRepository.save(traderStock);
            } else{
                TraderStock traderStock = new TraderStock(currentTrader,currentStock, quantity);
                traderStockRepository.save(traderStock);
            }
            traderService.subtractBalance(currentTrader.userId, totalCost);
        }
        ResponseEntity.status(HttpStatus.OK).body("Successfully bought the stock shares");
    }

}
