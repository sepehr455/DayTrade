package com.example.day_trade.UserStock;


import com.example.day_trade.Stock.Stock;
import com.example.day_trade.Trader.Trader;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"userId", "stockId"})})
public class TraderStock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName = "userId")
    public Trader trader;

    @ManyToOne
    @JoinColumn(name = "stockId", referencedColumnName = "stockId")
    public Stock stock;

    public int quantity;

    public TraderStock() {
    }

    public TraderStock(Trader trader, Stock stock, int quantity) {
        this.trader = trader;
        this.stock = stock;
        this.quantity = quantity;
    }

    public void addToQuantity(int amount) {
        this.quantity += amount;
    }
}
