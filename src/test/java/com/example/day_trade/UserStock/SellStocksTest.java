package com.example.day_trade.UserStock;

import com.example.day_trade.Stock.Stock;
import com.example.day_trade.Stock.StockRepository;
import com.example.day_trade.Stock.StockService;
import com.example.day_trade.Trader.Trader;
import com.example.day_trade.Trader.TraderRepository;
import com.example.day_trade.Trader.TraderService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
public class SellStocksTest {

    @Autowired
    private TraderStockRepository traderStockRepository;
    @Autowired
    private StockService stockService;
    @Autowired
    private TraderService traderService;
    @Autowired
    private TraderRepository traderRepository;
    @Autowired
    private StockRepository stockRepository;

    private TraderStockService traderStockService;
    private Trader testTrader;
    private Stock testStock;
    private TraderStock testTraderStock;

    @BeforeEach
    void setup() {
        traderStockService = new TraderStockService(traderStockRepository, stockService, traderService);
        testTrader = new Trader("John Doe", 200);
        testStock = new Stock("TEST", 50);
    }

    @AfterEach
    void tearDown() {
        traderStockRepository.deleteAll();
        traderRepository.deleteAll();
        stockRepository.deleteAll();
    }

    @Test
    void sellStocksWorks_2() {
        traderRepository.save(testTrader);
        stockRepository.save(testStock);

        // Creating a new instance of the user with the stock
        TraderStock testTraderStock = new TraderStock(testTrader, testStock, 5);
        traderStockRepository.save(testTraderStock);

        boolean sellStatus = traderStockService.sellStocks(testTraderStock, 4);

        // Getting the updated version of the Trader and TraderStock
        Optional<TraderStock> updatedTraderStock = traderStockRepository
                .findById(testTraderStock.getId());
        Trader updatedTrader = traderRepository.findById(testTrader.getUserId())
                .orElseThrow(() -> new AssertionError("Failed to retrieve updated trader"));

        Assertions.assertEquals(updatedTraderStock.get().getQuantity(), 1);
        Assertions.assertTrue(sellStatus);
        Assertions.assertEquals(updatedTrader.getCurrentBalance(), 400);
    }

}
