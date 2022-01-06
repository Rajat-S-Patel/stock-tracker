package com.example.stocktracker.Services;

import com.example.stocktracker.Models.Stock;
import com.example.stocktracker.Models.User;
import com.example.stocktracker.Repository.StockRepository;
import com.example.stocktracker.Repository.UserRepository;
import com.example.stocktracker.util.UserUtil;
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
        if(userRepository.existsById(user.getUsername())) return null;
        return userRepository.save(user);
    }

    public User getUserByUserName(String username){
        return userRepository.findById(username).orElse(null);
    }

    public Set<Stock> getUserStocks(String username){
        return userRepository.findById(username).get().getStocks();
    }

    public void addStockToUserWatchlist(String username,Stock stock) {
        if(username.equals(null)) return;
        try{
            User user = userRepository.findByUsername(username).get();
            Optional<Stock> stockOptional = stockRepository.findById(stock.getStockId());
            if(stockOptional.isPresent()){
                user.getStocks().add(stock);
                userRepository.save(user);
            }
        }catch (NoSuchElementException e){
            e.printStackTrace();
        }
    }

    public boolean deleteStockFromUserWatchlist(String username,String tickerName) {

        try{
            User user = userRepository.findByUsername(username).get();
            Optional<Stock> stockOptional = stockRepository.findById(tickerName);
            if(!stockOptional.isPresent()) return false;
            Set<Stock> userStocks = user.getStocks();
            userStocks.remove(stockOptional.get());
            user.setStocks(userStocks);
            userRepository.save(user);
            return true;
        }catch (NoSuchElementException e){
            e.printStackTrace();
        }
        return false;
    }
}
