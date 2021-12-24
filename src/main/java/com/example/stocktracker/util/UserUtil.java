package com.example.stocktracker.util;

import com.example.stocktracker.Models.Stock;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.NoSuchElementException;
import java.util.Set;

public class UserUtil {
    public static String getUsername(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        try{
            return  ((org.springframework.security.core.userdetails.User)authentication.getPrincipal()).getUsername();
        }catch (NullPointerException e){
            e.printStackTrace();
        }
        return null;
    }

}
