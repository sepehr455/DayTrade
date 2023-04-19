package com.example.day_trade.Trader;

import com.example.day_trade.UserStock.UserStock;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TraderService {

    private final TraderRepository userRepository;

    public TraderService(TraderRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Trader signup(String newUserName){

        Trader newUser = new Trader();
        newUser.setFullName(newUserName);
        return userRepository.save(newUser);
    }

    public Optional<List<UserStock>> getUserHoldings(String userId){

        Optional<Trader> stockOwner = userRepository.findById(userId);

        //this is same as calling the userHoldings method on the optional object of stockOwner
        return stockOwner.map(traders -> traders.userHoldings);

    }

}
