package com.example.day_trade.UserStock;

import com.example.day_trade.Stock.Stock;
import com.example.day_trade.Trader.Trader;

public class UserStockDto {

    private Trader user;

    private Stock stock;

    private int quantity;

    public UserStockDto(Trader user, Stock stock, int quantity) {
        this.user = user;
        this.stock = stock;
        this.quantity = quantity;
    }
}
