package com.example.day_trade.Trader;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class AddBalanceTest {

    @Autowired
    private TraderRepository traderRepository;
    private TraderService traderService;

    @BeforeEach
    void setup() {
        traderService = new TraderService(traderRepository);
    }

    @Test
    void addBalanceWorks() {
        Trader testTrader = new Trader("Sepehr", 22);
        traderRepository.save(testTrader);

        traderService.addBalance(testTrader.getUserId(), 18);
        assertEquals(testTrader.currentBalance, 40);
    }
}
