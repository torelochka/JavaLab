package ru.itis.zheleznov.services;

import org.springframework.security.crypto.password.PasswordEncoder;
import ru.itis.zheleznov.models.SignInForm;
import ru.itis.zheleznov.models.User;

import java.util.Optional;

public class SignInServiceJdbsImpl implements SignInService {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public SignInServiceJdbsImpl(UserService usersService, PasswordEncoder passwordEncoder) {
        this.userService = usersService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public boolean authenticate(SignInForm form) {
        Optional<User> userOptional = userService.getUserByEmail(form.getEmail());
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            return passwordEncoder.matches(form.getPassword(), user.getPasswordHash());
        }
        return false;
    }
}
