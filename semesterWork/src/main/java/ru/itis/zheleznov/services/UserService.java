package ru.itis.zheleznov.services;

import ru.itis.zheleznov.dto.UserDto;
import ru.itis.zheleznov.models.SignIn;
import ru.itis.zheleznov.models.SignUp;

import java.util.List;

public interface UserService {
    List<UserDto> getAll();
    boolean singIn(SignIn signIn);
    boolean signUp(SignUp signUp);
    UserDto getUser(Long id);
    UserDto getUser(String email);
}
