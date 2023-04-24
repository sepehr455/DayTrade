package com.example.day_trade.Stock;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long stock_id;

    String stockName;

    int stockPrice;

    public Stock() {
    }

    public Long getStock_id() {
        return stock_id;
    }

    public String getStockName() {
        return stockName;
    }

    public int getStockPrice() {
        return stockPrice;
    }

    public Stock(String stockName, int stockPrice) {
        this.stockName = stockName;
        this.stockPrice = stockPrice;
    }
}
