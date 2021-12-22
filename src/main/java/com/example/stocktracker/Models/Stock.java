package com.example.stocktracker.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long stockId;

    String name;

    @ManyToMany(mappedBy = "stocks")
    @JsonIgnore
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    Set<User> users = new HashSet<>();
}
