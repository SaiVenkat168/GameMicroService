package com.example.UserService.repository;

import com.example.UserService.entity.User;
import com.example.UserService.response.UsersResponse;
import jakarta.websocket.server.PathParam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Long>
{
    @Query(value = "SELECT password FROM gameapplication.user_table where email = :user or username = :user",nativeQuery = true)
    String getUserPassword(@Param("user") String user);

}
