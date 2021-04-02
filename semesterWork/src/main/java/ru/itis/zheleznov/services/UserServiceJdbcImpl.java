package ru.itis.zheleznov.services;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itis.zheleznov.dto.SignUpForm;
import ru.itis.zheleznov.models.User;
import ru.itis.zheleznov.repositories.UserRepository;

import java.util.List;
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
                .firstname(form.getFirstname())
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
        return userRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("user not found"));
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }
}
