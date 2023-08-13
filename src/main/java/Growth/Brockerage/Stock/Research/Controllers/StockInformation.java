package Growth.Brockerage.Stock.Research.Controllers;


import Growth.Brockerage.Stock.Research.DTOs.*;


import Growth.Brockerage.Stock.Research.ExternalEndpoints.SeekingAlpha;
import Growth.Brockerage.Stock.Research.Repository.*;


import com.fasterxml.jackson.core.JsonProcessingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.io.IOException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;


@RestController
public class StockInformation {

    @Autowired
    private WatchListDAOService watchListDAOService;

    @Autowired
    private WatchlistRepository watchlistRepository;

    @Autowired
    private SeekingAlpha seekingAlpha;

    @Autowired
    private StockListingsRepository stockListingsRepository;

    @Autowired
    private Watchlist_stock_mappingRepository watchlist_stock_mappingRepository;

    @Autowired
    RealTimeStockInfoRepository realTimeStockInfoRepository;


    @GetMapping(path = "/searchInformation/{query}")
    public ResponseEntity getStockSearchInformation(@PathVariable String query) {
        try {
            SearchResult searchResults = seekingAlpha.searchPhrase(query);
            return new ResponseEntity(searchResults, HttpStatus.OK);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping(path = "/realTimeQuotes")
    public void stockDetails() {

        List<StockListings> stockToMonitor = stockListingsRepository.findAll();
        ArrayList<String> sa_ids = new ArrayList<>();
        HashMap<Long, StockListings> stockListingsHashMap = new HashMap<>();
        for(StockListings stockListings : stockToMonitor){
            sa_ids.add(String.valueOf(stockListings.getSa_id()));
            stockListingsHashMap.put(stockListings.getSa_id(), stockListings);
        }

        try {
            List<RealTimeStockInfo> realTimeQuoteList = seekingAlpha.getRealTimeQuotes(sa_ids);
            for(RealTimeStockInfo realTimeStockInfo : realTimeQuoteList){
                StockListings stockListings = stockListingsHashMap.get(realTimeStockInfo.getSa_id());
                realTimeStockInfo.setStockListings(stockListings);
                realTimeStockInfo.setGrowthDBUpdatedAt(LocalDateTime.now());
                realTimeStockInfoRepository.save(realTimeStockInfo);
            }

            //return realTimeQuoteList;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping(path = "/addStockToWatchlist/{watchlistId}")
    public ResponseEntity<Watchlist> addStockToWatchlist(@RequestBody StockListings stockListings, @PathVariable int watchlistId ) throws JsonProcessingException {
        StockListings stockListingsDB = stockListingsRepository.findBySaIdAndSymbol(stockListings.getSa_id(), stockListings.getSymbol());
        if (stockListingsDB == null)
            stockListingsDB = stockListingsRepository.save(stockListings);

        Watchlist_stock_mapping watchlist_stock_mappingObject = new Watchlist_stock_mapping();
        watchlist_stock_mappingObject.setStockListings(stockListingsDB);
        Watchlist watchlistToReturn = watchlistRepository.findById(watchlistId).get();

        watchlist_stock_mappingObject.setWatchlist(watchlistToReturn);
        Watchlist_stock_mapping save = watchlist_stock_mappingRepository.save(watchlist_stock_mappingObject);
        List<Watchlist_stock_mapping> byWatchlistid = watchlist_stock_mappingRepository.findByWatchlistid(watchlistToReturn);
        List<StockListings> stockListingsList = new ArrayList<>();
        for(Watchlist_stock_mapping watchlist_stock_mapping : byWatchlistid){
            int stockListingsid = watchlist_stock_mapping.getStockListings().getId();
            Optional<StockListings> byId = stockListingsRepository.findById(stockListingsid);
            stockListingsList.add(byId.get());
        }

        watchlistToReturn.setStockList(stockListingsList);
        return new ResponseEntity(watchlistToReturn, HttpStatus.CREATED);
    }

    @PostMapping(path = "/createWatchlist")
    public ResponseEntity<Watchlist> createWatchlist(@RequestBody Watchlist watchlist) {
        Watchlist createdWatchlist = watchlistRepository.save(watchlist);
        return new ResponseEntity(createdWatchlist, HttpStatus.CREATED);
    }


    @GetMapping(path = "/getWatchlist")
    public List<Watchlist> getWatchList() {
        return watchlistRepository.findAll();
    }


}
