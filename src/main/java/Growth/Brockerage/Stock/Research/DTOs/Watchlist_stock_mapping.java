package Growth.Brockerage.Stock.Research.DTOs;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
public class Watchlist_stock_mapping {

    @Id
    @GeneratedValue
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Watchlist watchlist;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnore
    private StockListings stockListings;

    public Watchlist getWatchlist() {
        return watchlist;
    }

    public void setWatchlist(Watchlist watchlist) {
        this.watchlist = watchlist;
    }

    public StockListings getStockListings() {
        return stockListings;
    }

    public void setStockListings(StockListings stockListings) {
        this.stockListings = stockListings;
    }
}
