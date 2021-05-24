package ru.itis.zheleznov.services;

import ru.itis.zheleznov.dto.JwtResponse;
import ru.itis.zheleznov.dto.LoginRequest;
import ru.itis.zheleznov.dto.UserDto;

import java.util.Optional;

public interface SignInService {
    Optional<JwtResponse> signIn(LoginRequest loginRequest);
}
