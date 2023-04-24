package com.example.day_trade.UserStock;

import org.springframework.stereotype.Service;

@Service
public class TraderStockService {

    private final TraderStockRepository userStockRepository;

    public TraderStockService(TraderStockRepository userStockRepository){this.userStockRepository = userStockRepository;}

    public TraderStockDto userStockToUserStockDtoConverter (TraderStock traderStock){
        return new TraderStockDto(traderStock.getTrader(), traderStock.getStock(), traderStock.getQuantity());
    }

}
