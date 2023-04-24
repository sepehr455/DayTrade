package com.example.day_trade.UserStock;

import com.example.day_trade.Stock.Stock;
import com.example.day_trade.Trader.Trader;

public class TraderStockDto {

    public Trader trader;

    public Stock stock;

    public int quantity;

    public TraderStockDto(Trader trader, Stock stock, int quantity) {
        this.trader = trader;
        this.stock = stock;
        this.quantity = quantity;
    }
}
