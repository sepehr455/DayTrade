package com.example.day_trade.Entities;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Traders {
    @Id
    public String userId;

    public String fullName;

    int currentBalance;

    public Traders(){

    }

    public Traders(String userId, String fullName, int currentBalance) {
        this.userId = userId;
        this.fullName = fullName;
        this.currentBalance = currentBalance;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String user_id) {
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
