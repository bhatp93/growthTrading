package Growth.Brockerage.Stock.Research.DTOs;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class StockInventory {
    String inventoryName;
    LocalDateTime lastUpdated;

    List<StockListings> stockList = new ArrayList<>();
}
