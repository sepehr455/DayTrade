package Entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Stock {

    @Id
    String stock_id;

    String stockName;

    int stockPrice;

    public String getStock_id() {
        return stock_id;
    }

    public String getStockName() {
        return stockName;
    }

    public int getStockPrice() {
        return stockPrice;
    }

}
