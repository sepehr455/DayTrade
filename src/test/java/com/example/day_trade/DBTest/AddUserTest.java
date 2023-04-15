package com.example.day_trade;

import com.example.day_trade.Entities.Traders;
import com.example.day_trade.Repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class AddUserTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void addUserWorks() {

        Traders testUser = new Traders("22", "Sepehr", 0);

        // Creating a dummy user
        userRepository.save(testUser);

        // Retrieving the user
        Traders retrievedUser = userRepository.findById("22").orElseThrow();
        assertEquals(testUser.getFullName(), retrievedUser.getFullName());

    }
}
