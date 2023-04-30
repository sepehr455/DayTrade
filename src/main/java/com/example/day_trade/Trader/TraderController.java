package com.example.day_trade.Trader;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
public class TraderController {

    private final TraderService traderService;

    public TraderController(TraderService traderService){
        this.traderService = traderService;
    }

    @PostMapping("/user/add")
    public ResponseEntity<TraderDto> createUser(@RequestBody String newUserName) {
        Trader signedupTrader = traderService.signup(newUserName);
        TraderDto traderDto = new TraderDto(signedupTrader.getFullName());
        traderDto.setCurrentBalance(signedupTrader.getCurrentBalance());
        return ResponseEntity.ok(traderDto);
    }

    @PostMapping("/user/{userId}/add_balance")
    public ResponseEntity<TraderDto> addBalance(@PathVariable Long userId, @RequestBody String moneyToAdd){
        traderService.addBalance(userId, Integer.parseInt(moneyToAdd.strip()));
        Trader currentTrader = traderService.getTraderById(userId)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Unable to find the user with the given id"));
        TraderDto traderDto = new TraderDto(currentTrader.getFullName(), currentTrader.getCurrentBalance());
        return ResponseEntity.ok(traderDto);
    }
}
