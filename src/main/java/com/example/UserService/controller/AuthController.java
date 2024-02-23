package com.example.UserService.controller;

import com.example.UserService.entity.UserDetails;
import com.example.UserService.entity.UserLogin;
import com.example.UserService.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class AuthController
{


    private UserService service;
    @Autowired
    AuthController(UserService ser)
    {
        this.service=ser;
    }

    @PostMapping("/register")
    public ResponseEntity<String> createUser(@RequestBody UserDetails userDetails)
    {
        try
        {
            service.create(userDetails);
            return new ResponseEntity<>("Created Sucessfully", HttpStatusCode.valueOf(200));
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatusCode.valueOf(404));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserLogin login)
    {
        try
        {
            boolean flag=service.login(login);
            return flag?new ResponseEntity<>("Login Sucessfully", HttpStatusCode.valueOf(200)):new ResponseEntity<>("Login failed", HttpStatusCode.valueOf(404));
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatusCode.valueOf(406));
        }
    }
}
