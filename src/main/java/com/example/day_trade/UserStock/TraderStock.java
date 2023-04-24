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
    private Long id;

    @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName = "userId")
    private Trader trader;

    @ManyToOne
    @JoinColumn(name = "stockId", referencedColumnName = "stockId")
    private Stock stock;

    private int quantity;

    public Long getId() {
        return id;
    }

    public Trader getTrader() {
        return trader;
    }

    public Stock getStock() {
        return stock;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setTrader(Trader trader) {
        this.trader = trader;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
