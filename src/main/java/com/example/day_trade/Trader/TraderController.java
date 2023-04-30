package com.example.day_trade.Trader;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class TraderController {
    private final TraderService traderService;

    public TraderController(TraderService traderService){
        this.traderService = traderService;
    }

    @PostMapping("/user/add")
    public ResponseEntity<TraderDto> createUser(@RequestBody String newUserName) {
        Trader signedupTrader = traderService.signup(newUserName);
        TraderDto traderDto = new TraderDto(signedupTrader.getFullName(), signedupTrader.getCurrentBalance());
        return ResponseEntity.ok(traderDto);
    }

    @PostMapping("/user/{userId}/add_balance")
    public ResponseEntity<TraderDto> addBalance(@PathVariable Long userId, @RequestBody String moneyToAdd){
        Optional<Trader> trader = traderService.addBalance(userId, Integer.parseInt(moneyToAdd.strip()));
        Optional<TraderDto> traderDto = trader.map(TraderDto::new);
        return traderDto
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
}
