package com.example.UserService.repository;

import com.example.UserService.entity.UserDetails;
import com.example.UserService.response.UsersResponse;
import com.example.UserService.util.QueryBuilder;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserCustomRepo
{

    EntityManager entityManager;

    @Autowired
    UserCustomRepo(EntityManager entityManager)
    {
        this.entityManager=entityManager;
    }

    public List getAll()
    {
        try
        {
            String query="SELECT NEW UsersResponse(a.username,a.rating) FROM User a";
            return entityManager.createQuery(query, UsersResponse.class).getResultList();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            throw new RuntimeException("Unable tofetch users");
        }
    }

    public void updateById(UserDetails details,long id)
    {
        try
        {
            String set= QueryBuilder.getSet(details);
            String query="Update FROM User a SET "+set+" where a.id = "+id;
            entityManager.createQuery(query).executeUpdate();
            System.out.println(query);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            throw new RuntimeException("Unable tofetch users");
        }
    }
}
