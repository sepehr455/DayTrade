package com.example.day_trade.Trader;

import com.example.day_trade.UserStock.TraderStock;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TraderService {

    private final TraderRepository traderRepository;

    public TraderService(TraderRepository traderRepository) {
        this.traderRepository = traderRepository;
    }

    public Optional<Trader> getTraderById(Long userId) {
        return traderRepository.findById(userId);
    }

    public Trader signup(String newUserName) {
        Trader newUser = new Trader();
        newUser.setFullName(newUserName);
        newUser.setCurrentBalance(0);
        return traderRepository.save(newUser);
    }

    public Optional<List<TraderStock>> getUserHoldings(Long userId) {
        Optional<Trader> stockOwner = traderRepository.findById(userId);
        return stockOwner.map(Trader::getUserHoldings);
    }

    public Optional<Trader> addBalance(Long userId, int amount) {
        Optional<Trader> currentTrader = traderRepository.findById(userId);
        currentTrader.map(trader -> trader.addToBalance(amount));
        currentTrader.map(traderRepository::save);
        return currentTrader;
    }

    public Optional<Trader> subtractBalance(Long userId, int amount) {
        Optional<Trader> currentTrader = traderRepository.findById(userId);
        currentTrader.map(trader -> trader.subtractFromBalance(amount));
        currentTrader.map(traderRepository::save);
        return currentTrader;
    }
}
