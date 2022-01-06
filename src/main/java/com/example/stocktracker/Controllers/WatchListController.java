package com.example.stocktracker.Controllers;

import com.example.stocktracker.Models.Stock;
import com.example.stocktracker.Response.MessageResponse;
import com.example.stocktracker.Services.UserService;
import com.example.stocktracker.util.JWTUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Set;

@RestController
@CrossOrigin
public class WatchListController {
    @Value("${api.key}")
    private String key;
    private final JWTUtil jwtUtil;
    private final UserService userService;

    public WatchListController(JWTUtil jwtUtil, UserService userService) {
        this.jwtUtil = jwtUtil;
        this.userService = userService;
    }

    @GetMapping("/watchlist")
    public ResponseEntity<Set<Stock>> getUserWatchlist(@RequestHeader(name = "Authorization") String authHeader) {
        String username = jwtUtil.getUsernameFromToken(authHeader.substring(authHeader.indexOf(" ")+1));
        return ResponseEntity.ok().body(userService.getUserStocks(username));
    }

    @PostMapping("/watchlist")
    public ResponseEntity<MessageResponse> addStockToWatchList(@RequestHeader(value = "Authorization",required = true) String authHeader,@RequestBody Stock stock){
        String username = jwtUtil.getUsernameFromToken(authHeader.substring(authHeader.indexOf(" ")+1));
        userService.addStockToUserWatchlist(username,stock);
        return ResponseEntity.ok().body(new MessageResponse(MessageResponse.SUCCESS_TYPE,"Added to watchlist"));
    }

    @DeleteMapping("/watchlist/{tickerName}")
    public MessageResponse deleteStockFromUserWatchlist(@RequestHeader("Authorization") String authHeader,@PathVariable String tickerName){
        String userName = jwtUtil.getUsernameFromToken(authHeader.substring(authHeader.indexOf(" ")+1));
        boolean success = userService.deleteStockFromUserWatchlist(userName,tickerName);
        if (success) return new MessageResponse(MessageResponse.SUCCESS_TYPE,"deleted");
        return new MessageResponse(MessageResponse.ERROR_TYPE,"Error in deletion");
    }
}
