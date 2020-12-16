package ru.itis.zheleznov.services;

import ru.itis.zheleznov.models.User;
import ru.itis.zheleznov.repositories.UserRepository;

import java.util.Optional;

public class UserServiceJdbcImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceJdbcImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<User> getUserByEmail(User user) {
        return userRepository.getByEmail(user.getEmail());
    }

    @Override
    public void deleteUserByEmail(User user) {
        userRepository.delete(user);
    }
}
