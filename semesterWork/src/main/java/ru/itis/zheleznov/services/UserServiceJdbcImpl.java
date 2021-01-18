package ru.itis.zheleznov.services;

import org.springframework.stereotype.Service;
import ru.itis.zheleznov.models.User;
import ru.itis.zheleznov.repositories.UserRepository;

import java.util.Optional;

@Service
public class UserServiceJdbcImpl implements UserService {

    private UserRepository userRepository;

    public UserServiceJdbcImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void addUser(User user) {

    }

    @Override
    public void updateUser(User user) {

    }

    @Override
    public boolean userIsExist(String email) {
        return false;
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User getUserById(int id) {
        return null;
    }
}
