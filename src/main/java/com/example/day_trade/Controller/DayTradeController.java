package com.example.day_trade.Controller;

import com.example.day_trade.Dto.UserDto;
import com.example.day_trade.Entities.Traders;
import com.example.day_trade.Services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class DayTradeController {

    final private UserService userService;
    public DayTradeController(UserService userService) {
        this.userService = userService;
    }

    // Adding a new user
    @PostMapping("/user/add")
    public ResponseEntity<UserDto> getUserInfo(@RequestBody String newUserName) {
        Traders signedupTrader = userService.signup(newUserName);
        UserDto userDto = new UserDto(signedupTrader.getFullName());
        return ResponseEntity.ok(userDto);
    }

    // Method for getting a user's holding
//    @GetMapping("/user/{user_id}/holding")
//    public ResponseEntity<UserDto> getUserHolding(@RequestBody String userId){
//
//    }


}
