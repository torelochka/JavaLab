package ru.itis.zheleznov.services;

import ru.itis.zheleznov.models.User;

import java.util.Optional;

public interface UserService {
    Optional<User> getUserByEmail(User user);
    void deleteUserByEmail(User user);
}
