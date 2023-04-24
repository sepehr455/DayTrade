package com.example.day_trade.Trader;

import com.example.day_trade.UserStock.TraderStockDto;

import java.util.List;

public class TraderDto {

    public final String fullName;

    public int currentBalance;

    public List<TraderStockDto> userHoldings;

    public TraderDto(String fullName) {
        this.fullName = fullName;
    }

    public void setCurrentBalance(int currentBalance) {
        this.currentBalance = currentBalance;
    }

    public void setUserHoldings(List<TraderStockDto> userHoldings) {
        this.userHoldings = userHoldings;
    }
}
