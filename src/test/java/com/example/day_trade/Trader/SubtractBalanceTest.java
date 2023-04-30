package com.example.day_trade.Trader;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class SubtractBalanceTest {

    @Autowired
    private TraderRepository traderRepository;
    private TraderService traderService;

    @BeforeEach
    void setup() {
        traderService = new TraderService(traderRepository);
    }

    @Test
    void subtractBalanceWorks() {
        Trader testTrader = new Trader("Sepehr", 22);
        traderRepository.save(testTrader);

        traderService.subtractBalance(testTrader.getUserId(), 18);
        assertEquals(testTrader.getCurrentBalance(), 4);
    }

    @Test
    void subtractBalanceDeptTest() {
        Trader testTrader = new Trader("Sepehr", 22);
        traderRepository.save(testTrader);

        traderService.subtractBalance(testTrader.getUserId(), 23);
        assertEquals(testTrader.getCurrentBalance(), 22);
    }
}