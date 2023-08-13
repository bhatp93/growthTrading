package Growth.Brockerage.Stock.Research.ExternalEndpoints;

import Growth.Brockerage.Stock.Research.DTOs.RealTimeStockInfo;
import Growth.Brockerage.Stock.Research.DTOs.ScheduledTasks;
import Growth.Brockerage.Stock.Research.DTOs.SearchResult;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class SeekingAlpha {


    String baseURL = "https://seeking-alpha.p.rapidapi.com";
    String readyAPIKey = "679d0370c1msh22371eb32003d9dp1264efjsn375e25aec627";

    String readyAPIHost = "seeking-alpha.p.rapidapi.com";

    public List<RealTimeStockInfo> getRealTimeQuotes(List<String> sa_ids) throws IOException {

        String sa_id = sa_ids.get(0);
        for(int i=1; i<sa_ids.size(); i++)
            sa_id = sa_id+"%2C"+sa_ids.get(i);

        String realTimeQuotesUrl = baseURL+"/market/get-realtime-quotes?sa_ids="+sa_id;
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(realTimeQuotesUrl)
                .get()
                .addHeader("X-RapidAPI-Key", readyAPIKey)
                .addHeader("X-RapidAPI-Host", readyAPIHost)
                .build();

        Response response = client.newCall(request).execute();
        String responseBody = response.body().string();

        responseBody = responseBody.substring(20);


        List<RealTimeStockInfo> realTimeQuoteList;
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        realTimeQuoteList = objectMapper.readValue(responseBody, new TypeReference<>() {
        });

        return realTimeQuoteList;
    }

    public SearchResult searchPhrase(String searchPhrase) throws IOException{
        OkHttpClient client = new OkHttpClient();
        String searchPhraseURL = baseURL+"/v2/auto-complete?query="+searchPhrase+"&type=people%2Csymbols%2Cpages&size=5";
        Request request = new Request.Builder()
                .url(searchPhraseURL)
                .get()
                .addHeader("X-RapidAPI-Key", readyAPIKey)
                .addHeader("X-RapidAPI-Host", readyAPIHost)
                .build();

        Response response = client.newCall(request).execute();
        String responseBody = response.body().string();
        System.out.println("search body response is --------------------->");
        System.out.println(responseBody);
        SearchResult searchResult;
        ObjectMapper objectMapper = new ObjectMapper();
        //objectMapper.registerModule(new JavaTimeModule());
        searchResult = objectMapper.readValue(responseBody, new TypeReference<>(){});

        return searchResult;

    }
}
