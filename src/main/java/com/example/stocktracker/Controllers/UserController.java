package com.example.stocktracker.Controllers;

import com.example.stocktracker.Models.User;
import com.example.stocktracker.Response.MessageResponse;
import com.example.stocktracker.Response.LoginResponse;
import com.example.stocktracker.Services.UserService;
import com.example.stocktracker.config.MyUserDetailsService;
import com.example.stocktracker.util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin
public class UserController {
    private final MyUserDetailsService userDetailsService;
    private  final JWTUtil jwtUtil;
    private final AuthenticationManager authenticationManager;
    private final UserService userService;

    @Autowired
    private HttpServletRequest context;

    public UserController(MyUserDetailsService userDetailsService, JWTUtil jwtUtil, AuthenticationManager authenticationManager, UserService userService) {
        this.userDetailsService = userDetailsService;
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
        this.userService = userService;
    }


    @PostMapping("/user")
    public ResponseEntity<?> createUser(@RequestBody User user){
        User savedUser = userService.createUser(user);
        System.out.println("user: "+savedUser);
        if(savedUser == null) return ResponseEntity.ok(new MessageResponse(MessageResponse.ERROR_TYPE,"Username already registered"));
        return ResponseEntity.ok().body(savedUser);
    }

    @PostMapping("/login")
    ResponseEntity<?> createAuthToken(@RequestBody User user) throws BadCredentialsException {
        String username = user.getUsername();
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username,user.getPassword()));

        final UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        final String jwt = jwtUtil.generateToken(userDetails);

        return ResponseEntity.ok(new LoginResponse(jwt));
    }

}
