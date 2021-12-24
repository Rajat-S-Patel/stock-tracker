package com.example.stocktracker.POJO;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class StockNews {
    @JsonAlias("data")
    List<StockNewsSingle> news = new ArrayList<>();
}
