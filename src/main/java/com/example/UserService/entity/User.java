package com.example.UserService.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "USER_TABLE")
public class User
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "NAME",nullable = false)
    private String name;
    @Column(name = "EMAIL",unique = true,nullable = false)
    private String email;
    @Column(name = "PASSWORD",nullable = false)
    private String password;
    @Column(name = "USERNAME",unique = true,nullable = false)
    private String username;
    @Column(name = "ABOUT",nullable = false)
    private String about;
    @Column(name = "RATING",nullable = false)
    private int rating;
    @Column(name = "STATUS",nullable = false)
    private String status;
    @Column(name = "LAST_UPDATE_TIME",nullable = false)
    private Timestamp lastUpdated;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Timestamp getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Timestamp lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
    public User(String name, String email, String password, String username, String about, int rating, String status, Timestamp lastUpdated) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.username = username;
        this.about = about;
        this.rating = rating;
        this.status = status;
        this.lastUpdated = lastUpdated;
    }
}
