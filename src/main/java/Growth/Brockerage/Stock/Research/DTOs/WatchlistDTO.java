package Growth.Brockerage.Stock.Research.DTOs;

import org.springframework.stereotype.Component;

import java.util.List;

public class WatchlistDTO {
    private String name;
    private List<StockListings> stockList;

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
