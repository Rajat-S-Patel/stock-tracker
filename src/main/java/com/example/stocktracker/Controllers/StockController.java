package com.example.stocktracker.Controllers;

import com.example.stocktracker.POJO.TrendingStockResponse.Quote;
import com.example.stocktracker.Response.TrendingStockResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class StockController {
    @Value("${api.key}")
    private String key;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${api.GET_STOCK_BY_TICKER}")
    private String GET_STOCK_BY_TICKER; // = "https://yfapi.net/v6/finance/quote?region=IN&lang=en&symbols=";
    @Value("${api.GET_STOCK_SUMMARY_PREFIX}")
    private String GET_STOCK_SUMMARY_PREFIX; //="https://yfapi.net/v11/finance/quoteSummary/";
    @Value("${api.TRENDING_STOCKS}")
    private String TRENDING_STOCKS; //="https://yfapi.net/v1/finance/trending/";
    @Value("${api.MARKET_SUMMARY}")
    private String MARKET_SUMMARY; //="https://yfapi.net/v6/finance/quote/marketSummary?lang=en&region=";
    @Value("${api.STOCK_HISTORY}")
    private String STOCK_HISTORY;
    @Value("${api.CHART_DATA}")
    private String CHART_DATA;

    private ResponseEntity<String> apiCaller(String uri) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .setHeader("X-API-KEY",key)
                .setHeader("Content-Type","application/json")
                .method("GET",HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request,HttpResponse.BodyHandlers.ofString());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<String>(response.body(),headers, HttpStatus.ACCEPTED);
    }

    @GetMapping("/get-stock/{tickerName}")
    public ResponseEntity<String> getStock(@PathVariable String tickerName) throws IOException, InterruptedException {
        return apiCaller(GET_STOCK_BY_TICKER + tickerName);
    }

    @GetMapping("/get-stock-summary")   //  get-stock-summary?ticker=AAPL&region=IN&modules=summaryDetail
    public ResponseEntity<String> getStockSummary(@RequestParam(name = "ticker") String tickerName,
                                                  @RequestParam(required = false,defaultValue = "IN") String region,
                                                  @RequestParam(required = false,defaultValue = "summaryDetail") String modules)
            throws IOException, InterruptedException {

        return apiCaller(GET_STOCK_SUMMARY_PREFIX
                            +tickerName
                            +"?lang=en"
                            +"&region="+region
                            +"&modules="+modules);
    }

    @GetMapping("/get-trending-stocks/{region}") // ONLY US REGION IS RESPONDING W PROPER DATA
    public ResponseEntity<String> getTrendingStocks(@PathVariable String region) throws IOException, InterruptedException {
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-API-KEY",key);
        HttpEntity<String> httpEntity = new HttpEntity<>("body",headers);
        ResponseEntity<TrendingStockResponse> r = restTemplate.exchange(TRENDING_STOCKS+region,HttpMethod.GET,httpEntity, TrendingStockResponse.class);
        TrendingStockResponse sr = r.getBody();
        if(sr!=null){
            if(sr.getFinance()!=null && sr.getFinance().getResult()!=null && sr.getFinance().getResult().size()>0 && sr.getFinance().getResult().get(0).getCount()>0){
                List<Quote> quotes = sr.getFinance().getResult().get(0).getQuotes();
                String trendingStocks = quotes.stream().map(quote->quote.getSymbol()).collect(Collectors.joining(","));
                return getStock(trendingStocks);
            }
        }
        return null;
    }

    @GetMapping("/get-market-summary")  // ?region=US
    public ResponseEntity<String> getMarketSummary(@RequestParam(required = false,defaultValue = "IN") String region) throws IOException, InterruptedException {
        return apiCaller(MARKET_SUMMARY+region);
    }

    @GetMapping("/stock-history")
    public ResponseEntity<String> getStockHistory(@RequestParam String tickers,
                                                  @RequestParam(required = false,defaultValue = "5y") String range,
                                                  @RequestParam(required = false,defaultValue = "1mo") String interval)
            throws IOException, InterruptedException {

        return apiCaller(STOCK_HISTORY
                            +"?interval="+interval
                            +"&range="+range
                            +"&symbols="+tickers);
    }

    @GetMapping("/get-chart-data/{tickerName}") //AAPL?comparisons=MSFT%2C%5EVIX&range=1mo&region=US&interval=1d&lang=en&events=div%2Csplit
    public ResponseEntity<String> getCharData(@PathVariable String tickerName,
                                              @RequestParam(required = false,defaultValue = "")String comparisons,
                                              @RequestParam(required = false,defaultValue = "1mo") String range,
                                              @RequestParam(required = false,defaultValue = "IN") String region,
                                              @RequestParam(required = false,defaultValue = "1d") String interval,
                                              @RequestParam(required = false,defaultValue = "div,split") String events)
            throws IOException, InterruptedException {
        return apiCaller(CHART_DATA
                            +tickerName
                            +"?comparisons="+comparisons
                            +"&range="+range
                            +"&region="+region
                            +"&interval="+interval
                            +"&lang=en"
                            +"&events="+events);
    }
}
