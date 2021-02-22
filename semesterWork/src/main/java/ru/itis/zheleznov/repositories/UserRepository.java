package ru.itis.zheleznov.repositories;

import ru.itis.zheleznov.models.User;

import java.util.Optional;

public interface UserRepository {
    boolean save(User user);

    Optional<User> findByEmail(String email);

    Optional<User> findById(long id);
}
