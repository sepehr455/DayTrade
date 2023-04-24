package com.example.day_trade.DBTest;

import com.example.day_trade.Trader.Trader;
import com.example.day_trade.Trader.TraderRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class AddUserTest {

    @Autowired
    private TraderRepository userRepository;

    @Test
    void addUserWorks() {

        Trader testUser = new Trader(22L, "Sepehr", 0);

        // Creating a dummy user
        userRepository.save(testUser);

        // Retrieving the user
        Trader retrievedUser = userRepository.findById(22L).orElseThrow();
        assertEquals(testUser.getFullName(), retrievedUser.getFullName());

    }
}
