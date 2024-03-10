package com.example.tasklist.repository;


import com.example.tasklist.model.user.Role;
import com.example.tasklist.model.user.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Optional;

//@Mapper
public interface UserRepository {

    Optional<User> findById(Long userId);

    Optional<User> findByUsername(String username);

    void update(User user);

    void create(User user);

    void insertUserRole(@Param("userId") Long userId, @Param("role") Role role);

    boolean isTaskOwner(@Param("userId") Long userId, @Param("taskId") Long taskId);

    void delete(Long id);

}
