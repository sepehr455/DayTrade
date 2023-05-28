package com.example.day_trade.Stock;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StockService {

    private final StockRepository stockRepository;

    public StockService(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    public Optional<Stock> getStockDetails(String stockName) {
        return stockRepository.findByStockName(stockName);
    }

    public Optional<Stock> getStockByName(String stockName) {
        return stockRepository.findByStockName(stockName);
    }

    public StockDto stockToStockDtoConverter(Stock currentStock) {
        return new StockDto(currentStock.stockName, currentStock.stockPrice);
    }

}
