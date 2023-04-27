package com.example.day_trade.Trader;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.web.server.ResponseStatusException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@DataJpaTest
public class AddUserTest {

    @Autowired
    private TraderRepository userRepository;

    @Test
    void addUserWorks() {

        Trader testUser = new Trader("Sepehr", 0);

        // Creating a dummy user
        userRepository.save(testUser);

        Long currentUserId = testUser.getUserId();

        // Retrieving the user
        Trader retrievedUser = userRepository.findById(currentUserId)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Unable to find the user with the given id"));
        assertEquals(testUser.getFullName(), retrievedUser.getFullName());

    }
}
