package ru.itis.zheleznov.repositories;


import ru.itis.zheleznov.models.User;

public interface UsersRepository {
    boolean save(User user);

}
