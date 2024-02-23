package com.example.UserService.entity;


import lombok.*;

//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
public class UserDetails
{
    private String name;
    private String email;
    private String password;
    private String username;
    private String about;

    public UserDetails(String name, String email, String password, String username, String about) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.username = username;
        this.about = about;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }
}
