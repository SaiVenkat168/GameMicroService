package com.example.UserService.util;

import com.example.UserService.entity.UserDetails;

public class QueryBuilder
{
    public static String getSet(UserDetails details)
    {
        String s="a.username = '"
                +details.getUsername()
                +"' , a.password = '"
                +details.getPassword()
                +"' , a.name = '"
                +details.getName()
                +"' , a.email = '"
                +details.getEmail()
                +"' , a.about ='"
                +details.getAbout()+"'";
        return s;
    }
}
