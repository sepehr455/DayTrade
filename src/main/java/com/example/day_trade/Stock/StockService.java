package com.example.day_trade.Stock;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StockService {

    private final StockRepository stockRepository;

    public StockService(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    // Method for creating a stock (mainly used to create mock stocks)
    public void createNewStock(String stockName, int stockPrice){
        Stock newStock = new Stock(stockName, stockPrice);
        stockRepository.save(newStock);
    }

    // Method for returning the stock with the given name
    public Optional<Stock> getStockDetails(String stockName){
        return stockRepository.findByStockName(stockName);
    }

    public StockDto stockToStockDtoConverter (Stock currentStock){
        return new StockDto(currentStock.stockName, currentStock.stockPrice);
    }

}
