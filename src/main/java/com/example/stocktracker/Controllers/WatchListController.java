package com.example.stocktracker.Controllers;

import com.example.stocktracker.Models.Stock;
import com.example.stocktracker.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
public class WatchListController {
    @Value("${api.key}")
    private String key;

    @Autowired
    UserService userService;

    @Autowired
    StockController stockController;

    @GetMapping("/watchlist")
    public ResponseEntity<String> getUserWatchlist() throws IOException, InterruptedException {
        Set<Stock> stocks = userService.getUserStocks();
        if(stocks.isEmpty()){
            return ResponseEntity.badRequest().build();
        }
        return stockController.getStock(stocks.stream().map(stock->stock.getName()).collect(Collectors.joining(",")));
    }

    @PostMapping("/watchlist")
    public void addStockToWatchList(@RequestBody Stock stock){
        userService.addStockToUserWatchlist(stock);
    }

    @DeleteMapping("/watchlist/{tickerName}")
    public void deleteStockFromUserWatchlist(@PathVariable String tickerName){
        userService.deleteStockFromUserWatchlist(tickerName);
    }
}
