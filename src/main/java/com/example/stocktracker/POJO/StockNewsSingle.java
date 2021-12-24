package com.example.stocktracker.POJO;


import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
public class StockNewsSingle {
    private UUID uuid;
    private String title;
    private String description;
    private String url;
    @JsonAlias({"image_url"})
    private String imageUrl;

    @JsonAlias({"published_at"})
    private Date publishedAt;

}
