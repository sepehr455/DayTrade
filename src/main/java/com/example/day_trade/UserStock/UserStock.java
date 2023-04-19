package com.example.day_trade.UserStock;


import com.example.day_trade.Stock.Stock;
import com.example.day_trade.Trader.Trader;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"userId", "stock_id"})})
public class UserStock {

    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName = "userId")
    private Trader user;

    @ManyToOne
    @JoinColumn(name = "stock_id", referencedColumnName = "stock_id")
    private Stock stock;

    private int quantity;

    public Long getId() {
        return id;
    }

    public Trader getUser() {
        return user;
    }

    public Stock getStock() {
        return stock;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setUser(Trader user) {
        this.user = user;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
