package Growth.Brockerage.Stock.Research.DTOs;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;


import Growth.Brockerage.Stock.Research.ExternalEndpoints.SeekingAlpha;
import Growth.Brockerage.Stock.Research.Repository.RealTimeStockInfoRepository;
import Growth.Brockerage.Stock.Research.Repository.StockListingsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
@ConditionalOnProperty(name = "scheduler.enabled", matchIfMissing = true)
public class ScheduledTasks {
    @Autowired
    private SeekingAlpha seekingAlpha;

    @Autowired
    StockListingsRepository stockListingsRepository;

    @Autowired
    RealTimeStockInfoRepository realTimeStockInfoRepository;

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    static int i = 0;

    @Scheduled(cron = "* * */2 * * *")
    public void reportCurrentTime() {
        //i++;
        //System.out.println("timer note " + i + " index  " + dateFormat.format(new Date()));
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
}