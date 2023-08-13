package Growth.Brockerage.Stock.Research.Repository;

import Growth.Brockerage.Stock.Research.DTOs.Watchlist;
import Growth.Brockerage.Stock.Research.DTOs.Watchlist_stock_mapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Watchlist_stock_mappingRepository extends JpaRepository<Watchlist_stock_mapping, Integer> {

        @Query("SELECT wlm from Watchlist_stock_mapping wlm WHERE wlm.watchlist = :watchlistId")
        List<Watchlist_stock_mapping> findByWatchlistid(Watchlist watchlistId);
}
