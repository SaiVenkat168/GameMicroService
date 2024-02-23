package com.example.UserService.controller;

import com.example.UserService.entity.User;
import com.example.UserService.entity.UserDetails;
import com.example.UserService.repository.UserRepository;
import com.example.UserService.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController
{

    private UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping("/")
    public List getAll()
    {
        return service.getUsers();
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateUser(@RequestBody UserDetails details,@PathVariable("id") long id)
    {
        try {
            service.update(details,id);
            return new ResponseEntity<>("Updated Sucessfully", HttpStatus.OK);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            throw new RuntimeException("Unable to update User");
        }
    }

    @GetMapping("/{id}")
    public User getById(@PathVariable("id") long id)
    {
        try {
            return service.getById(id);
           // return new ResponseEntity<>("Updated Sucessfully", HttpStatus.OK);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            throw new RuntimeException("Unable to update User");
        }
    }

    @DeleteMapping("/{id}")
    public List deleteById(@PathVariable("id") long id)
    {
        try {
            return service.deleteById(id);
            // return new ResponseEntity<>("Updated Sucessfully", HttpStatus.OK);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            throw new RuntimeException("Unable to update User");
        }
    }

}
