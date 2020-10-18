package ru.itis.zheleznov.services;

import ru.itis.zheleznov.repositories.AuthRepository;

import javax.servlet.http.Cookie;
import java.util.UUID;

public class AuthServiceImpl implements AuthService {

    private final AuthRepository authRepository;

    public AuthServiceImpl(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }

    @Override
    public void addCookieAuth(Cookie cookie) {
        authRepository.create(cookie);
    }

    @Override
    public boolean findCookieAuth(Cookie cookie) {
        return authRepository.find(cookie);
    }
}
