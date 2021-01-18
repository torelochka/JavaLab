package ru.itis.zheleznov.services;

import ru.itis.zheleznov.models.User;

import java.util.Optional;

public interface UserService {
    void addUser(User user);

    void updateUser(User user);

    boolean userIsExist(String email);

    Optional<User> getUserByEmail(String email);

    User getUserById(int id);
}
