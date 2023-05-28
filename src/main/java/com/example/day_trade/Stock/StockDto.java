package com.example.day_trade.Stock;

import com.fasterxml.jackson.annotation.JsonProperty;

public class StockDto {
    @JsonProperty("stock_name")
    public final String stockName;

    @JsonProperty("stock_price")
    public final int stockPrice;

    public StockDto(String stockName, int stockPrice) {
        this.stockName = stockName;
        this.stockPrice = stockPrice;
    }
}
