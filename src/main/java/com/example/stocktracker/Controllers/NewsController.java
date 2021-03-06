package com.example.stocktracker.Controllers;

import com.example.stocktracker.Services.NewsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

@RestController
@CrossOrigin
public class NewsController {
    private final NewsService newsService;

    public NewsController(NewsService newsService) {
        this.newsService = newsService;
    }

    @GetMapping("/news")
    public ResponseEntity<?> getNewsByUUID(@RequestParam UUID uuid){
        System.out.println(uuid);
        return ResponseEntity.ok().body(newsService.getNewsByUUID(uuid));
    }

    @GetMapping("/news/all")
    public ResponseEntity<?> getAllNews(@RequestParam Map<String,String> params){
        return ResponseEntity.ok().body(newsService.getAllNews(params));
    }
}
