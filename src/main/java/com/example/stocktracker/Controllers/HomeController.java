package com.example.stocktracker.Controllers;

import com.example.stocktracker.Response.SearchResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class HomeController {

    @Value("${api.key}")
    private String key;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${api.AUTOCOMPLETE}")
    private String AUTOCOMPLETE; //="https://yfapi.net/v6/finance/autocomplete?region=IN&lang=en&query=";

    @GetMapping("/search/{query}")
    public SearchResponse getSearchResult(@PathVariable String query){
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-API-KEY",key);
        HttpEntity<String> httpEntity = new HttpEntity<>("body",headers);
        ResponseEntity<SearchResponse> r = restTemplate.exchange(AUTOCOMPLETE+query,HttpMethod.GET,httpEntity, SearchResponse.class);
        SearchResponse sr = r.getBody();
        return sr;
    }
}
