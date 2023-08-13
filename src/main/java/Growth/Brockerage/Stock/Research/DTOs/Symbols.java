package Growth.Brockerage.Stock.Research.DTOs;

public class Symbols {
    long id;
    String name;
    String slug;
    String content;
    String exchange;
    String currency;
    int is_quant_rated;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getExchange() {
        return exchange;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public int getIs_quant_rated() {
        return is_quant_rated;
    }

    public void setIs_quant_rated(int is_quant_rated) {
        this.is_quant_rated = is_quant_rated;
    }
}
