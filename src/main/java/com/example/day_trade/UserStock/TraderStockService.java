package com.example.day_trade.UserStock;

import com.example.day_trade.Stock.Stock;
import com.example.day_trade.Stock.StockService;
import com.example.day_trade.Trader.Trader;
import com.example.day_trade.Trader.TraderService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TraderStockService {

    private final TraderStockRepository traderStockRepository;
    private final TraderService traderService;
    private final StockService stockService;

    public TraderStockService(TraderStockRepository userStockRepository, StockService stockService, TraderService traderService) {
        this.traderStockRepository = userStockRepository;
        this.traderService = traderService;
        this.stockService = stockService;
    }

    public TraderStockDto userStockToUserStockDtoConverter(TraderStock traderStock) {
        return new TraderStockDto(traderStock.getTrader(), traderStock.getStock(), traderStock.getQuantity());
    }

    public void updateQuantity(int quantity, TraderStock traderStock, int sign) {
        traderStock.addToQuantity(sign * quantity);
        traderStockRepository.save(traderStock);
    }

    public Optional<TraderStock> getTraderStock(Long userId, String stockName) {
        Optional<Trader> currentTrader = traderService.getTraderById(userId);
        Optional<Stock> currentStock = stockService.getStockByName(stockName);

        return currentTrader.flatMap(trader ->
                currentStock.flatMap(stock -> traderStockRepository.findByTraderUserIdAndStockStockId(trader.getUserId(), stock.getStock_id())
                )
        );
    }

    public boolean buyStock(Trader currentTrader, Stock currentStock, int quantity) {
        boolean purchaseStatus = false;
        int totalCost = currentStock.getStockPrice() * quantity;

        // If the user is able to afford the stock
        if (currentTrader.getCurrentBalance() >= totalCost) {
            TraderStock traderStock = traderStockRepository
                    .findByTraderUserIdAndStockStockId(currentTrader.getUserId(), currentStock.getStock_id())
                    .orElse(new TraderStock(currentTrader, currentStock, 0));

            updateQuantity(quantity, traderStock, 1);
            traderService.subtractBalance(currentTrader.getUserId(), totalCost);
            purchaseStatus = true;
        }

        return purchaseStatus;
    }

    public boolean sellStocks(TraderStock traderStock, int quantity) {
        boolean sellStatus = false;

        int totalCost = traderStock.getStock().getStockPrice() * quantity;

        // If the user owns enough shares to be sold
        if (traderStock.getQuantity() >= quantity) {
            updateQuantity(quantity, traderStock, -1);
            traderService.addBalance(traderStock.getTrader().getUserId(), totalCost);
            sellStatus = true;
        }

        return sellStatus;
    }
}
