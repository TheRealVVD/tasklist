package com.example.tasklist.service;

import com.example.tasklist.model.user.User;

/**
 * Класс, содержащий методы, которые будут вызываться из контроллера для работы с User'ом
 */
public interface UserService {

    User getById(Long id);

    User getUserByUsername(String username);

    User update(User user);

    User create(User user);

    boolean isTaskOwner(Long userId, Long taskId);

    void delete(Long userId);

}
