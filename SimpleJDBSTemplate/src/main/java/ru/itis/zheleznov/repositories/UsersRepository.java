package ru.itis.zheleznov.repositories;

import ru.itis.zheleznov.models.User;

import java.util.List;

public interface UsersRepository extends CrudRepository<User> {
    List<User> findAllByAge(int age);
    List<User> findAllByName(String name);
}

