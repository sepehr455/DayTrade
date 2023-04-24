package com.example.day_trade.UserStock;

import com.example.day_trade.Stock.Stock;
import com.example.day_trade.Trader.Trader;

public class TraderStockDto {

    private Trader trader;

    private Stock stock;

    private int quantity;

    public TraderStockDto(Trader user, Stock stock, int quantity) {
        this.trader = user;
        this.stock = stock;
        this.quantity = quantity;
    }
}
