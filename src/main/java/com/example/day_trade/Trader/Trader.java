package com.example.day_trade.Trader;


import com.example.day_trade.UserStock.TraderStock;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity
public class Trader {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userId")
    public Long userId;

    public String fullName;

    public int currentBalance;

    @OneToMany
    public List<TraderStock> userHoldings;

    public Trader(){

    }

    public Trader(Long userId, String fullName, int currentBalance) {
        this.userId = userId;
        this.fullName = fullName;
        this.currentBalance = currentBalance;
    }

    public Trader(String fullName, int currentBalance) {
        this.fullName = fullName;
        this.currentBalance = currentBalance;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long user_id) {
        this.userId = user_id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getCurrentBalance() {
        return currentBalance;
    }

    public void setCurrentBalance(int currentBalance) {
        this.currentBalance = currentBalance;
    }
}
