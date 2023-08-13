package Growth.Brockerage.Stock.Research.Repository;

import Growth.Brockerage.Stock.Research.DTOs.StockListings;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface StockListingsRepository extends JpaRepository<StockListings, Integer> {

    @Query("SELECT sl FROM StockListings sl WHERE sl.sa_id = :saId AND sl.symbol = :symbol")
    StockListings findBySaIdAndSymbol(long saId, String symbol);
}
