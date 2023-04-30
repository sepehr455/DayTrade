package com.example.day_trade.Trader;

import com.example.day_trade.UserStock.TraderStockDto;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class TraderDto {

    @JsonProperty("trader_name")
    private final String fullName;

    @JsonProperty("trader_balance")
    private int currentBalance;

    @JsonProperty("user_holdings")
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
