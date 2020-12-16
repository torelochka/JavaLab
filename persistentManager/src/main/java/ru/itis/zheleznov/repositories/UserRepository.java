package ru.itis.zheleznov.repositories;

import ru.itis.zheleznov.models.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    List<User> getAll();
    Optional<User> getById(long id);
    Optional<User> getByEmail(String email);
    void save(User user);
    void update(User user);
    void delete(User user);
}
