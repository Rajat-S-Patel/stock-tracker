package com.example.stocktracker.Models;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "app_user")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
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
    @ToString.Exclude
    private Set<Stock> stocks=new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        User user = (User) o;

        return Objects.equals(username, user.username);
    }

    @Override
    public int hashCode() {
        return 562048007;
    }
}
