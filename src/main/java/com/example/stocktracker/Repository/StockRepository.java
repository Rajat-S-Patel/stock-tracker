package com.example.stocktracker.Repository;

import com.example.stocktracker.Models.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface StockRepository extends JpaRepository<Stock,String> {
    Optional<Stock> findByName(String name);
}
