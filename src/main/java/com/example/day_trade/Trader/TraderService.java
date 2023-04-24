package com.example.day_trade.Trader;

import com.example.day_trade.UserStock.TraderStock;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    // Trader to Trader Dto Converter
//    public Optional<TraderDto> TraderToTraderDtoConverter(Trader trader){
//        TraderDto convertedTrader = new TraderDto(trader.fullName);
//        convertedTrader.currentBalance = trader.currentBalance;
//
//    }

}
