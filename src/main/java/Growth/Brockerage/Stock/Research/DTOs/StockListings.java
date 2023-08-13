package Growth.Brockerage.Stock.Research.DTOs;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class StockListings {
    @Id
    @GeneratedValue
    @JsonIgnore
    private int id;
    private String symbol;
    private String name;

    private long sa_id;

    @OneToMany(mappedBy = "stockListings")
    @JsonIgnore
    private List<Watchlist_stock_mapping> watchlist_stock_mapping;

    @OneToMany(mappedBy = "stockListings")
    @JsonIgnore
    private List<RealTimeStockInfo> realTimeStockInfos;

    public int getId() {
        return id;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getSa_id() {
        return sa_id;
    }

    public void setSa_id(long sa_id) {
        this.sa_id = sa_id;
    }

    public List<Watchlist_stock_mapping> getWatchlist_stock_mapping() {
        return watchlist_stock_mapping;
    }

    public void setWatchlist_stock_mapping(List<Watchlist_stock_mapping> watchlist_stock_mapping) {
        this.watchlist_stock_mapping = watchlist_stock_mapping;
    }
}
