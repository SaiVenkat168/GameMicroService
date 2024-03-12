package com.example.UserService.service;

import com.example.UserService.entity.User;
import com.example.UserService.entity.UserDetails;
import com.example.UserService.entity.UserLogin;
import com.example.UserService.repository.UserCustomRepo;
import com.example.UserService.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.security.sasl.AuthenticationException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Service
public class UserService
{

    @Autowired
    public UserRepository repository;

//    UserService(UserRepository rep)
//    {
//        this.repository=rep;
//    }


    @Autowired
    public UserCustomRepo repo;

//    UserService(UserCustomRepo rep)
//    {
//        this.repo=rep;
//    }

   public User create(UserDetails details)
   {
       User user=setAllValues(details);
       repository.save(user);
       return user;
   }

   public boolean login(UserLogin login)
   {
       String user=login.getUsername();
       String password= login.getPassword();
       String psw=repository.getUserPassword(user);
       System.out.println(psw);
       return psw.equals(password);
   }

    private User setAllValues(UserDetails details)
    {
        User usr=new User( details.getName(),
                           details.getEmail(),
                           details.getPassword(),
                           details.getUsername(),
                           details.getAbout(),
                           1400,"Active",new Timestamp(new Date().getTime()));
        return usr;
    }

    public List getUsers() {
        //return repository.findAll();
        try
        {
        return repo.getAll();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            throw new RuntimeException("Error in fetching user details");
        }
    }

    public void update(UserDetails details,long id)
    {
        try {
            repo.updateById(details,id);
        }
        catch (Exception e)
        {
            throw new RuntimeException("Unable to update user");
        }
    }

    public User getById(long id)
    {
        try
        {
            if(!repository.existsById(id))
                throw new RuntimeException("User Not Found");
            return repository.findById(id).get();
        }
        catch (Exception e)
        {
            throw new RuntimeException("Unable to get user");
        }
    }

    public List deleteById(long id)
    {
        try
        {
            if(!repository.existsById(id))
                throw new RuntimeException("User Not Found");
            repository.deleteById(id);
            return getUsers();
        }
        catch (Exception e)
        {
            throw new RuntimeException("Unable to get user");
        }
    }

    public void isAuthorized(String usr,String pass)
    {
        if(usr.equals("root") && pass.equals("root@2024"))
            return ;
        throw new RuntimeException("Found unAuthorized to access this api");
    }
}
