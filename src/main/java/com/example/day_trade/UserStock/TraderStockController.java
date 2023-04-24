package com.example.day_trade.UserStock;

import com.example.day_trade.Stock.Stock;
import com.example.day_trade.Stock.StockDto;
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
public class TraderStockController {

    final private TraderService traderService;
    final private TraderStockService userStockService;
    final private StockService stockService;


    public TraderStockController(TraderService userService, TraderStockService userStockService, StockService stockService) {
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
    public List<TraderStockDto> getUserHolding(@PathVariable Long user_id) {
        return traderService.getUserHoldings(user_id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Unable to find the user with the given id"))
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

    // Method for getting info about the stock
    @GetMapping("/stock/{stockName}")
    public ResponseEntity<StockDto> getStockInfo(@PathVariable String stockName){
        Stock stock = stockService.getStockDetails(stockName)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "No stock found with the given name"));
        StockDto stockDto = stockService.stockToStockDtoConverter(stock);
        return ResponseEntity.ok(stockDto);
    }
}
