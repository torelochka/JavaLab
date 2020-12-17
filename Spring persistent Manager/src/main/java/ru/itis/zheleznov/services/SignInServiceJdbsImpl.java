package ru.itis.zheleznov.services;

import org.springframework.security.crypto.password.PasswordEncoder;
import ru.itis.zheleznov.models.SignInForm;
import ru.itis.zheleznov.models.User;
import ru.itis.zheleznov.repositories.UserRepository;

import java.util.Optional;

public class SignInServiceJdbsImpl implements SignInService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public SignInServiceJdbsImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public boolean authenticate(SignInForm form) {
        Optional<User> userOptional = userRepository.getByEmail(form.getEmail());

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            return passwordEncoder.matches(form.getPassword(), user.getHash());
        }
        return false;
    }
}
