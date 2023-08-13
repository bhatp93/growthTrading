package Growth.Brockerage.Stock.Research.Repository;

import Growth.Brockerage.Stock.Research.DTOs.Watchlist;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class WatchListDAOService {
    static List<Watchlist> watchListDB = new ArrayList<>();
    static int i=-1;
    public int createWatchList(Watchlist watchlist){
        watchListDB.add(watchlist);
        return 1;
    }

    public List<Watchlist> getWatchlist(){
        return watchListDB;
    }
}
