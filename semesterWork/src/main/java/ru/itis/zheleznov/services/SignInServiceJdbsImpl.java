package ru.itis.zheleznov.services;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itis.zheleznov.dto.SignInForm;
import ru.itis.zheleznov.models.User;

import java.util.Optional;

@Service
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
