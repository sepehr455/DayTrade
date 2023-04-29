package com.example.day_trade.UserStock;

import com.example.day_trade.Stock.Stock;
import com.example.day_trade.Trader.Trader;

public class TraderStockDto {

    private final Trader trader;
    private final Stock stock;
    private final int quantity;

    public TraderStockDto(Trader trader, Stock stock, int quantity) {
        this.trader = trader;
        this.stock = stock;
        this.quantity = quantity;
    }

    public Trader getTrader() {
        return trader;
    }

    public Stock getStock() {
        return stock;
    }

}
