package Growth.Brockerage.Stock.Research.DTOs;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Watchlist {
    @Id
    @GeneratedValue
    private int id;
    private String name;

    @OneToMany(mappedBy = "watchlist")
    @JsonIgnore
    private List<Watchlist_stock_mapping> watchlist_stock_mapping;

    @Transient()
    private List<StockListings> stockList;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<StockListings> getStockList() {
        return stockList;
    }

    public void setStockList(List<StockListings> stockList) {
        this.stockList = stockList;
    }
}
