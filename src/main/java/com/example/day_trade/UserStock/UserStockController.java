package com.example.day_trade.UserStock;

import com.example.day_trade.Trader.Trader;
import com.example.day_trade.Trader.TraderDto;
import com.example.day_trade.Trader.TraderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class UserStockController {

    final private TraderService userService;

    public UserStockController(TraderService userService) {
        this.userService = userService;
    }

    // Adding a new user
    @PostMapping("/user/add")
    public ResponseEntity<TraderDto> getUserInfo(@RequestBody String newUserName) {
        Trader signedupTrader = userService.signup(newUserName);
        TraderDto userDto = new TraderDto(signedupTrader.getFullName());
        return ResponseEntity.ok(userDto);
    }

//    //    Method for getting a user's holding
//    @GetMapping("/user/{user_id}/holding")
//    public List<UserStockDto> getUserHolding(@RequestBody String userId) {
//
////        UserDto userDto = new UserDto(userService.)
////        Optional<List<UserStockDto>> currentTrader = userService.getUserHoldings(userId);
////
////
////
////
////        return outputList;
//
//    }


//    @PostMapping("/user/{userId}/add_balance")
//    public

}
