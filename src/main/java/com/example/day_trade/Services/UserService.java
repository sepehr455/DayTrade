package com.example.day_trade.Services;

import com.example.day_trade.Entities.Traders;
import com.example.day_trade.Repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Traders signup(String newUserName){

        Traders newUser = new Traders();
        newUser.setFullName(newUserName);
        return userRepository.save(newUser);
    }

}
