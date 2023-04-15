package com.example.day_trade.Entities;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class User {
    @Id
    String user_id;

    String fullName;

    int currentBalance;

    public String getUser_id() {
        return user_id;
    }

    public String getFullName() {
        return fullName;
    }

    public int getCurrentBalance() {
        return currentBalance;
    }

    public void setCurrentBalance(int currentBalance) {
        this.currentBalance = currentBalance;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    // A method for testing purposes
    public User createNewUser(String user_id, String fullName) {
        User newUser = new User();
        newUser.setFullName(fullName);
        newUser.user_id = user_id;
        newUser.setCurrentBalance(0);
        return newUser;
    }

}
