package ru.itis.zheleznov.services;

import ru.itis.zheleznov.dto.SignUpForm;
import ru.itis.zheleznov.models.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    void addUser(SignUpForm form);

    boolean userIsExist(String email);

    Optional<User> getUserByEmail(String email);

    User getUserById(long id);

    List<User> getAll();

    User save(User user);
}
