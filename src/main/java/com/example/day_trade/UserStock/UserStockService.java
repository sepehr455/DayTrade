package com.example.day_trade.UserStock;

import org.springframework.stereotype.Service;

@Service
public class UserStockService {

    private final UserStockRepository userStockRepository;

    public UserStockService(UserStockRepository userStockRepository){this.userStockRepository = userStockRepository;}

    public UserStockDto userStockToUserStockDtoConverter (UserStock userStock){
        return new UserStockDto(userStock.getUser(), userStock.getStock(), userStock.getQuantity());
    }



    public UserStockRepository getUserStockRepository() {
        return userStockRepository;
    }
}
