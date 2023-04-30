package com.example.day_trade.Stock;

public class StockDto {
    private final String stockName;
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
