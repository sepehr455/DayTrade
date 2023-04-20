package com.example.day_trade.UserStock;

import com.example.day_trade.Trader.Trader;
import com.example.day_trade.Trader.TraderDto;
import com.example.day_trade.Trader.TraderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class UserStockController {

    final private TraderService traderService;
    final private UserStockService userStockService;

    public UserStockController(TraderService userService, UserStockService userStockService) {
        this.traderService = userService;
        this.userStockService = userStockService;
    }

    // Adding a new user
    @PostMapping("/user/add")
    public ResponseEntity<TraderDto> getUserInfo(@RequestBody String newUserName) {
        Trader signedupTrader = traderService.signup(newUserName);
        TraderDto userDto = new TraderDto(signedupTrader.getFullName());
        return ResponseEntity.ok(userDto);
    }

    //  Method for getting a user's holding
    @GetMapping("/user/{user_id}/holding")
    public List<UserStockDto> getUserHolding(@PathVariable String user_id) {

        return traderService.getUserHoldings(user_id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Unable to find resource"))
                .stream()
                .map(userStockService::userStockToUserStockDtoConverter).toList();

    }
}
