package ru.itis.zheleznov.services;

import ru.itis.zheleznov.dto.UserDto;

import java.util.Optional;

public interface UserService {
    Optional<UserDto> userByEmail(String email);
    Optional<UserDto> userByEmailAndPassword(String email, String password);
    Optional<UserDto> userById(Long id);
    void banUser(Long id);
}
