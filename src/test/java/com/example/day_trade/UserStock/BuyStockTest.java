package com.example.day_trade.UserStock;

import com.example.day_trade.Stock.Stock;
import com.example.day_trade.Stock.StockRepository;
import com.example.day_trade.Stock.StockService;
import com.example.day_trade.Trader.Trader;
import com.example.day_trade.Trader.TraderRepository;
import com.example.day_trade.Trader.TraderService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest
class BuyStockTest {

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

    // If the user does not currently have a share of the stock
    @Test
    void buyStockWorks_1() {
        traderRepository.save(testTrader);
        stockRepository.save(testStock);

        boolean buyStatus = traderStockService.buyStock(testTrader, testStock, 4);

        Optional<TraderStock> testTraderStockOptional = traderStockRepository
                .findByTraderUserIdAndStockStockId(testTrader.userId, testStock.stockId);
        Trader updatedTrader = traderRepository.findById(testTrader.userId)
                .orElseThrow(() -> new AssertionError("Failed to retrieve updated trader"));

        TraderStock testTraderStock = testTraderStockOptional.get();

        assertEquals(testTraderStock.quantity, 4);
        assertEquals(testTraderStock.trader.userId, testTrader.userId);
        assertEquals(testTraderStock.stock.stockId, testStock.stockId);
        assertEquals(updatedTrader.currentBalance, 0);
        assertTrue(buyStatus);

    }

    // If the user already owns a share of the stock
    @Test
    void buyStocksWorks_2() {
        traderRepository.save(testTrader);
        stockRepository.save(testStock);

        // Creating a new instance of the user with the stock
        TraderStock testTraderStock = new TraderStock(testTrader, testStock, 2);
        traderStockRepository.save(testTraderStock);
        boolean buyStatus = traderStockService.buyStock(testTrader, testStock, 4);

        // Getting the updated version of the Trader and TraderStock
        Optional<TraderStock> updatedTraderStock = traderStockRepository
                .findById(testTraderStock.id);
        Trader updatedTrader = traderRepository.findById(testTrader.userId)
                .orElseThrow(() -> new AssertionError("Failed to retrieve updated trader"));

        assertEquals(updatedTraderStock.get().quantity, 6);
        assertEquals(updatedTrader.currentBalance, 0);
        assertTrue(buyStatus);

    }

}