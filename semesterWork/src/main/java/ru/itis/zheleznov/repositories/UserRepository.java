package ru.itis.zheleznov.repositories;

import ru.itis.zheleznov.models.User;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User> {
    Optional<User> getUser(String email, String hash);
    Optional<User> getUser(String email);
}
