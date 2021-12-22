package com.example.stocktracker.config;

import com.example.stocktracker.Services.UserService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MyUserDetailsService implements UserDetailsService {
    private final UserService userService;

    public MyUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.example.stocktracker.Models.User user = userService.getUserByUserName(username);
        System.out.println(user+" "+username);
        return new User(user.getUsername(),user.getPassword(),new ArrayList<>());
    }

//    private Collection<? extends GrantedAuthority> getAuthorities(Role role) {
//        List<GrantedAuthority> authorities = new ArrayList<>();
//        authorities.add(new SimpleGrantedAuthority("ROLE_"+role.getRoleName()));
//        for(Permission permission:role.getPermissions()){
//            authorities.add(new SimpleGrantedAuthority(permission.getPermissionName()));
//        }
//        return authorities;
//    }
//
//    private Collection<? extends GrantedAuthority> getGrantedPermissions(Set<Permission> permissions) {
//        List<GrantedAuthority> authorities = new ArrayList<>();
//
//        for(Permission permission:permissions){
//            authorities.add(new SimpleGrantedAuthority(permission.getPermissionName()));
//        }
//        return authorities;
//    }

}
