package com.example.day_trade.Stock;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
public class StockController {
    private final StockService stockService;

    public StockController(StockService stockService){
        this.stockService = stockService;
    }

    @GetMapping("/stock/{stockName}")
    public ResponseEntity<StockDto> getStockInfo(@PathVariable String stockName){
        Stock stock = stockService.getStockDetails(stockName)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "No stock found with the given name"));
        StockDto stockDto = stockService.stockToStockDtoConverter(stock);
        return ResponseEntity.ok(stockDto);
    }
}
