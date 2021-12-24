package com.example.stocktracker.Services;

import com.example.stocktracker.POJO.StockNews;
import com.example.stocktracker.POJO.StockNewsSingle;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.*;

@Service
public class NewsService {

    @Value("${newapi.key}")
    private String apiKey;
    private String NewsUrl = "https://api.marketaux.com/v1/news/";

    public StockNewsSingle getNewsByUUID(UUID uuid){
        RestTemplate restTemplate = new RestTemplate();
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(NewsUrl+"uuid/"+uuid);
        builder.queryParam("api_token",apiKey);
        StockNewsSingle response = restTemplate.getForObject(builder.toUriString(),StockNewsSingle.class);

        return response;
    }

    public StockNews getAllNews(Map<String,String> params){
        RestTemplate restTemplate  = new RestTemplate();

        UriComponentsBuilder builder= UriComponentsBuilder.fromUriString(NewsUrl+"all");
        for (Map.Entry<String, String> entry : params.entrySet()) {
            builder.queryParam(entry.getKey(),entry.getValue());
        }
        builder.queryParam("api_token",apiKey);
        StockNews response = restTemplate.getForObject(builder.toUriString(),StockNews.class);
        return response;
    }
}
