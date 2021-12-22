package com.example.stocktracker.Services;

import com.example.stocktracker.POJO.StockNews;
import com.example.stocktracker.POJO.StockNewsSingle;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class NewsService {

    @Value("${newapi.key}")
    private String apiKey;
    private String NewsUrl = "https://api.marketaux.com/v1/news/";

    public StockNewsSingle getNewsByUUID(UUID uuid){
        RestTemplate restTemplate = new RestTemplate();
        String finalUrl = NewsUrl + "uuid/" + uuid + "?api_token=" + apiKey;
        StockNewsSingle response = restTemplate.getForObject(finalUrl,StockNewsSingle.class);

        return response;
    }

    public StockNews getAllNews(){
        RestTemplate restTemplate  = new RestTemplate();
        String finalUrl = NewsUrl + "all?api_token="+apiKey;
        StockNews response = restTemplate.getForObject(finalUrl,StockNews.class);
        System.out.println("news: "+response);
        return response;
    }
}
