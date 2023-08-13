package Growth.Brockerage.Stock.Research.Repository;

import Growth.Brockerage.Stock.Research.DTOs.Watchlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WatchlistRepository extends JpaRepository<Watchlist, Integer> {

}
