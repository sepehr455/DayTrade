package com.example.day_trade.Trader;

import com.example.day_trade.UserStock.TraderStockDto;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class TraderDto {

    @JsonProperty("trader_name")
    public final String fullName;

    @JsonProperty("trader_balance")
    public int currentBalance;

    @JsonProperty("user_holdings")
    public List<TraderStockDto> userHoldings;

    public TraderDto(String fullName) {
        this.fullName = fullName;
    }

    public TraderDto(String fullName, int currentBalance) {
        this.fullName = fullName;
        this.currentBalance = currentBalance;
    }

    public TraderDto(Trader trader) {
        this.fullName = trader.fullName;
        this.currentBalance = trader.currentBalance;
    }

}
