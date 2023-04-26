package com.example.day_trade.Trader;

import com.example.day_trade.UserStock.TraderStock;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@SuppressWarnings("WriteOnlyObject")
@Service
public class TraderService {

    private final TraderRepository traderRepository;


    public TraderService(TraderRepository traderRepository) {
        this.traderRepository = traderRepository;
    }


    // Method for finding a trader with the given userID
    public Optional<Trader> getTraderById(Long userId) {
        return traderRepository.findById(userId);
    }

    // A method that creates a new trader with the given username
    public Trader signup(String newUserName) {

        Trader newUser = new Trader();
        newUser.setFullName(newUserName);
        newUser.setCurrentBalance(0);
        return traderRepository.save(newUser);
    }

    public Optional<List<TraderStock>> getUserHoldings(Long userId) {
        Optional<Trader> stockOwner = traderRepository.findById(userId);
        return stockOwner.map(traders -> traders.userHoldings);
    }

    public void addBalance(Long userId, int amount) {
        Trader currentTrader = traderRepository.findById(userId).
                orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Unable to find the user with the given id"));
        currentTrader.currentBalance += amount;
        traderRepository.save(currentTrader);
    }

    // Trader to Trader Dto Converter
//    public Optional<TraderDto> TraderToTraderDtoConverter(Trader trader){
//        TraderDto convertedTrader = new TraderDto(trader.fullName);
//        convertedTrader.currentBalance = trader.currentBalance;
//
//    }

}
