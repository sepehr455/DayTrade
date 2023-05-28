package com.example.day_trade.Stock;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stockId")
    public Long stockId;

    public String stockName;
    public int stockPrice;

    public Stock() {
    }

    public Stock(String stockName, int stockPrice) {
        this.stockName = stockName;
        this.stockPrice = stockPrice;
    }

    public Stock(Long stockId, String stockName, int stockPrice) {
        this.stockId = stockId;
        this.stockName = stockName;
        this.stockPrice = stockPrice;
    }
}
