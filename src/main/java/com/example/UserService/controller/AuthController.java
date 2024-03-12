package com.example.UserService.controller;

import com.example.UserService.entity.UserDetails;
import com.example.UserService.entity.UserLogin;
import com.example.UserService.service.SessionService;
import com.example.UserService.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.naming.AuthenticationException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class AuthController
{


    @Autowired
    private SessionService sessionService;



    private UserService service;
    @Autowired
    AuthController(UserService ser)
    {
        this.service=ser;
    }

    @PostMapping("/register")
    public ResponseEntity<String> createUser(@RequestBody UserDetails userDetails, HttpSession session, HttpServletRequest request)
    {
        String authorizationHeader = request.getHeader("Authorization");
        String username = "";
        String password = "";
        if (authorizationHeader != null && authorizationHeader.startsWith("Basic ")) {
            String base64Credentials = authorizationHeader.substring("Basic ".length());
            String credentials = new String(java.util.Base64.getDecoder().decode(base64Credentials));
            String[] parts = credentials.split(":", 2);
            username = parts[0];
            password = parts[1];
        }

        try
        {
            service.isAuthorized(username,password);
            service.create(userDetails);
            Map<String, String> sessionData = new HashMap<>();
            sessionData.put("username", userDetails.getUsername());
            sessionData.put("email",userDetails.getEmail());
            sessionService.createSession(session.getId(),sessionData);
            return new ResponseEntity<>("Created Sucessfully", HttpStatusCode.valueOf(200));
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatusCode.valueOf(404));
        }
    }

    /*
    @PostMapping("/register")
    public String createUser(@RequestBody UserDetails userDetails, HttpSession session)
    {
        try
        {
            service.create(userDetails);
            return "redirect:/login";
        }
        catch (Exception e)
        {
           throw new RuntimeException("Error in creating user registeration");
        }
    }
    * */

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserLogin login,HttpSession session,HttpServletRequest request)
    {
        String authorizationHeader = request.getHeader("Authorization");
        String username = "";
        String password = "";
        if (authorizationHeader != null && authorizationHeader.startsWith("Basic ")) {
            String base64Credentials = authorizationHeader.substring("Basic ".length());
            String credentials = new String(java.util.Base64.getDecoder().decode(base64Credentials));
            String[] parts = credentials.split(":", 2);
            username = parts[0];
            password = parts[1];
        }

        try
        {
            service.isAuthorized(username,password);
            boolean flag=service.login(login);
            if(flag)
            {
                Map<String, String> sessionData = new HashMap<>();
                sessionData.put("username", login.getUsername());
                sessionService.createSession(session.getId(),sessionData);
            }
            return flag?new ResponseEntity<>("Login Sucessfully", HttpStatusCode.valueOf(200)):new ResponseEntity<>("Login failed", HttpStatusCode.valueOf(404));
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatusCode.valueOf(406));
        }
    }
}
