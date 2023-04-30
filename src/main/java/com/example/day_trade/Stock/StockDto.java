package com.example.day_trade.Stock;

import com.fasterxml.jackson.annotation.JsonProperty;

public class StockDto {

    @JsonProperty("stock_name")
    private final String stockName;

    @JsonProperty("stock_price")
    private final int stockPrice;

    public StockDto(String stockName, int stockPrice) {
        this.stockName = stockName;
        this.stockPrice = stockPrice;
    }

    public String getStockName() {
        return stockName;
    }

    public int getStockPrice() {
        return stockPrice;
    }
}
