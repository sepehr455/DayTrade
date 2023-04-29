package com.example.day_trade.Trader;

import com.example.day_trade.UserStock.TraderStockDto;

import java.util.List;

public class TraderDto {

    private final String fullName;

    private int currentBalance;

    private List<TraderStockDto> userHoldings;

    public TraderDto(String fullName) {
        this.fullName = fullName;
    }

    public TraderDto(String fullName, int currentBalance) {
        this.fullName = fullName;
        this.currentBalance = currentBalance;
    }

    public void setCurrentBalance(int currentBalance) {
        this.currentBalance = currentBalance;
    }
}
