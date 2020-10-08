package ru.itis.zheleznov.services;

import ru.itis.zheleznov.models.User;

import java.util.List;

public interface UsersService {
    List<User> getAllUsers();
    List<User> getAllUsersByAge(int age);
    List<User> getAllUsersByName(String name);
}
