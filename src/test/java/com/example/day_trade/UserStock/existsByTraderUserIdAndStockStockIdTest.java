package com.example.day_trade.UserStock;

import com.example.day_trade.Stock.Stock;
import com.example.day_trade.Stock.StockRepository;
import com.example.day_trade.Trader.Trader;
import com.example.day_trade.Trader.TraderRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
public class existsByTraderUserIdAndStockStockIdTest {

    @Autowired
    private TraderRepository traderRepository;
    @Autowired
    private StockRepository stockRepository;
    @Autowired
    private TraderStockRepository traderStockRepository;

    @Test
    void existsByTraderUserIdAndStockStockId() {

        Trader testTrader = new Trader("sepehr", 22);
        Stock testStock = new Stock("testStock", 22);

        traderRepository.save(testTrader);
        stockRepository.save(testStock);

        Long traderId = testTrader.getUserId();
        Long stockId = testStock.getStock_id();

        TraderStock testTraderStock = new TraderStock(testTrader, testStock, 4);
        traderStockRepository.save(testTraderStock);

        assertTrue(traderStockRepository.existsByTraderUserIdAndStockStockId(traderId, stockId));
    }
}
