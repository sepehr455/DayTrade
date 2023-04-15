package Entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "stock_id"})})
public class UserStock {

    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "stock_id", referencedColumnName = "stock_id")
    private Stock stock;

    private int quantity;

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Stock getStock() {
        return stock;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
