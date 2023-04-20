package com.example.day_trade.Trader;

import com.example.day_trade.UserStock.UserStockDto;

import java.util.List;

public class TraderDto {

    public final String fullName;

    int currentBalance;

    public List<UserStockDto> userHoldings;

    public TraderDto(String fullName) {
        this.fullName = fullName;
    }

    public void setCurrentBalance(int currentBalance) {
        this.currentBalance = currentBalance;
    }

    public void setUserHoldings(List<UserStockDto> userHoldings) {
        this.userHoldings = userHoldings;
    }
}
