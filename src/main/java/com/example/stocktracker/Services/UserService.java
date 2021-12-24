package com.example.stocktracker.Services;

import com.example.stocktracker.Models.Stock;
import com.example.stocktracker.Models.User;
import com.example.stocktracker.Repository.StockRepository;
import com.example.stocktracker.Repository.UserRepository;
import com.example.stocktracker.util.UserUtil;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {
    private final UserRepository userRepository;

    private final StockRepository stockRepository;

    public UserService(UserRepository userRepository, StockRepository stockRepository) {
        this.userRepository = userRepository;
        this.stockRepository = stockRepository;
    }

    public User createUser(User user){
        return userRepository.save(user);
    }

    public User getUserByUserName(String username){
        return userRepository.findById(username).orElse(null);
    }

    public Set<Stock> getUserStocks(){

        String username = UserUtil.getUsername();
        try{
            User user = userRepository.findByUsername(username).get();
            return user.getStocks();
        }catch (NoSuchElementException e){
            e.printStackTrace();
        }
        return null;
    }

    public void addStockToUserWatchlist(Stock stock) {
        String username = UserUtil.getUsername();
        if(username.equals(null)) return;
        try{
            User user = userRepository.findByUsername(username).get();
            Optional<Stock> stockOptional = stockRepository.findByName(stock.getName());
            if(!stockOptional.isPresent()) return;
            Set<Stock> userStocks = user.getStocks();
            userStocks.add(stockOptional.get());
            user.setStocks(userStocks);
            userRepository.save(user);
        }catch (NoSuchElementException e){
            e.printStackTrace();
        }
    }

    public void deleteStockFromUserWatchlist(String tickerName) {
        String username = UserUtil.getUsername();
        try{
            User user = userRepository.findByUsername(username).get();
            Optional<Stock> stockOptional = stockRepository.findByName(tickerName);
            if(!stockOptional.isPresent()) return;
            Set<Stock> userStocks = user.getStocks();
            userStocks.remove(stockOptional.get());
            user.setStocks(userStocks);
            userRepository.save(user);
        }catch (NoSuchElementException e){
            e.printStackTrace();
        }
    }
}
