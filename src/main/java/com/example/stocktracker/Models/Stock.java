package com.example.stocktracker.Models;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Stock {
    @Id
    @JsonAlias({"stock_id","stockId"})
    String stockId;

    @Column(nullable = false)
    String name;

    @ManyToMany(mappedBy = "stocks")
    @JsonIgnore
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    Set<User> users = new HashSet<>();

    public Stock(String stockId,String name){

    }
}
