package Growth.Brockerage.Stock.Research.Repository;

import Growth.Brockerage.Stock.Research.DTOs.RealTimeStockInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RealTimeStockInfoRepository extends JpaRepository<RealTimeStockInfo, Integer> {

}
