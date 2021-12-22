package com.example.stocktracker.Models;

import lombok.Data;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "app_user")
@Data
public class User {

    @Id
    private String username;
    private String password;

    @ManyToMany
    @JoinTable(
            name = "user_stock",
            joinColumns = @JoinColumn(name = "username"),
            inverseJoinColumns = @JoinColumn(name = "stockId")
    )
    private Set<Stock> stocks = new HashSet<>();
}
