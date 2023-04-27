package com.example.day_trade.Stock;

import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static org.springframework.http.HttpStatus.NOT_FOUND;

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

    public Optional<Stock> getStockById(Long stockId){ return stockRepository.findById(stockId); }

    public Long getStockId(String stockName){
        Stock currentStock = stockRepository.findByStockName(stockName)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "No stock found with the given name"));
        return currentStock.stockId;
    }

    public StockDto stockToStockDtoConverter (Stock currentStock){
        return new StockDto(currentStock.getStockName(), currentStock.getStockPrice());
    }

}
