package Growth.Brockerage.Stock.Research.DTOs;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZoneOffset;

@Entity
public class RealTimeStockInfo {

    @Id
    @GeneratedValue
    int id;
    long sa_id;
    String sa_slug;
    String symbol ;
    double high;
    double low;
    double open;
    double close;
    double prev_close;
    double last;
    long volume;
    LocalDateTime last_time;
    long market_cap;

    LocalDateTime ext_time;

    double ext_price;

    String ext_market;
    String info;
    String src;
    LocalDateTime updated_at;

    LocalDateTime growthDBUpdatedAt;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnore
    private StockListings stockListings;

    public long getSa_id() {
        return sa_id;
    }

    public void setSa_id(long sa_id) {
        this.sa_id = sa_id;
    }

    public String getSa_slug() {
        return sa_slug;
    }

    public void setSa_slug(String sa_slug) {
        this.sa_slug = sa_slug;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public double getHigh() {
        return high;
    }

    public void setHigh(double high) {
        this.high = high;
    }

    public double getLow() {
        return low;
    }

    public void setLow(double low) {
        this.low = low;
    }

    public double getOpen() {
        return open;
    }

    public void setOpen(double open) {
        this.open = open;
    }

    public double getClose() {
        return close;
    }

    public void setClose(double close) {
        this.close = close;
    }

    public double getPrev_close() {
        return prev_close;
    }

    public void setPrev_close(double prev_close) {
        this.prev_close = prev_close;
    }

    public double getLast() {
        return last;
    }

    public void setLast(double last) {
        this.last = last;
    }

    public long getVolume() {
        return volume;
    }

    public void setVolume(long volume) {
        this.volume = volume;
    }

    public LocalDateTime getLast_time() {
        return last_time;
    }

    public void setLast_time(String lastTimeInString) {
        Instant instant = Instant.parse(lastTimeInString);
        this.last_time = LocalDateTime.ofInstant(instant, ZoneId.of(ZoneOffset.UTC.getId()));
    }

    public long getMarket_cap() {
        return market_cap;
    }

    public void setMarket_cap(long market_cap) {
        this.market_cap = market_cap;
    }


    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public LocalDateTime getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updatedAtString) {
        Instant instant = Instant.parse(updatedAtString);
        this.updated_at = LocalDateTime.ofInstant(instant, ZoneId.of(ZoneOffset.UTC.getId()));

    }

    public LocalDateTime getExt_time() {
        return ext_time;
    }

    public void setExt_time(String extTimeString) {
        Instant instant = Instant.parse(extTimeString);
        this.ext_time = LocalDateTime.ofInstant(instant, ZoneId.of(ZoneOffset.UTC.getId()));
    }

    public double getExt_price() {
        return ext_price;
    }

    public void setExt_price(double ext_price) {
        this.ext_price = ext_price;
    }

    public String getExt_market() {
        return ext_market;
    }

    public void setExt_market(String ext_market) {
        this.ext_market = ext_market;
    }

    public StockListings getStockListings() {
        return stockListings;
    }

    public void setStockListings(StockListings stockListings) {
        this.stockListings = stockListings;
    }

    public LocalDateTime getGrowthDBUpdatedAt() {
        return growthDBUpdatedAt;
    }

    public void setGrowthDBUpdatedAt(LocalDateTime growthDBUpdatedAt) {
        this.growthDBUpdatedAt = growthDBUpdatedAt;
    }
}
