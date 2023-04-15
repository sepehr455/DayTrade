package com.example.day_trade.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Stock {

    @Id
    String stock_id;

    String stockName;

    int stockPrice;

    public String getStock_id() {
        return stock_id;
    }

    public String getStockName() {
        return stockName;
    }

    public int getStockPrice() {
        return stockPrice;
    }

}
