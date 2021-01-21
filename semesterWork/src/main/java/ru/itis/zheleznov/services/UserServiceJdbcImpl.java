package ru.itis.zheleznov.services;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itis.zheleznov.models.SignUpForm;
import ru.itis.zheleznov.models.User;
import ru.itis.zheleznov.repositories.UserRepository;

import java.util.Optional;

@Service
public class UserServiceJdbcImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    public UserServiceJdbcImpl(UserRepository userRepository, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    @Override
    public void addUser(SignUpForm form) {
        User user = User.builder()
                .name(form.getFirstname())
                .lastname(form.getLastname())
                .email(form.getEmail())
                .passwordHash(encoder.encode(form.getPassword()))
                .build();
        userRepository.save(user);
    }

    @Override
    public boolean userIsExist(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User getUserById(long id) {
        return userRepository.findById(id).orElse(null);
    }
}
