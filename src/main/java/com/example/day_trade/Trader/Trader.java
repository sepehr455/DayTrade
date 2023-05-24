package com.example.day_trade.Trader;


import com.example.day_trade.UserStock.TraderStock;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.List;
import java.util.Optional;

@Entity
public class Trader {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userId")
    Long userId;

    private String fullName;
    private int currentBalance;

    @OneToMany
    private List<TraderStock> userHoldings;

    public Trader() {
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

    public List<TraderStock> getUserHoldings() {
        return userHoldings;
    }

    public Optional<Trader> addToBalance(int amount) {
        this.currentBalance += amount;
        return Optional.of(this);
    }

    public Optional<Trader> subtractFromBalance(int amount) {
        if (this.currentBalance >= amount) {
            this.currentBalance -= amount;
        }
        return Optional.of(this);
    }
}
