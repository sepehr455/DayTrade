package com.example.day_trade.UserStock;

import com.example.day_trade.Stock.StockService;
import com.example.day_trade.Trader.Trader;
import com.example.day_trade.Trader.TraderDto;
import com.example.day_trade.Trader.TraderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
public class UserStockController {

    final private TraderService traderService;
    final private UserStockService userStockService;
    final private StockService stockService;


    public UserStockController(TraderService userService, UserStockService userStockService, StockService stockService) {
        this.traderService = userService;
        this.userStockService = userStockService;
        this.stockService = stockService;
    }

    // Adding a new user
    @PostMapping("/user/add")
    public ResponseEntity<TraderDto> createUser(@RequestBody String newUserName) {
        Trader signedupTrader = traderService.signup(newUserName);
        TraderDto traderDto = new TraderDto(signedupTrader.getFullName());
        traderDto.setCurrentBalance(signedupTrader.getCurrentBalance());
        return ResponseEntity.ok(traderDto);
    }

    //  Method for getting a user's holding
    @GetMapping("/user/{user_id}/holding")
    public List<UserStockDto> getUserHolding(@PathVariable Long user_id) {
        return traderService.getUserHoldings(user_id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Unable to find resource"))
                .stream()
                .map(userStockService::userStockToUserStockDtoConverter).toList();

    }

    // Used for adding stocks
//    @PostMapping("stock/addTestStocks")
//    public void addTestStocks(){
//        stockService.createNewStock("Tesla", 23);
//        stockService.createNewStock("Apple", 100);
//        stockService.createNewStock("Amazon", 43);
//        stockService.createNewStock("Adidas", 88);
//    }

//    // Method for getting info about the stock
//    @GetMapping("/stock/{stock_id}")
//    public ResponseEntity<Stock> getStockInfo(@PathVariable Long stock_id){
//
//
//
//
//    }
}
