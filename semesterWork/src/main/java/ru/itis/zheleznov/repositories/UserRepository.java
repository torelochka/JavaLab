package ru.itis.zheleznov.repositories;

import ru.itis.zheleznov.models.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    void save(User user);

    Optional<User> findByEmail(String email);

    Optional<User> findById(long id);
}
