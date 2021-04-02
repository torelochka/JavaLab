package ru.itis.zheleznov.services;

import ru.itis.zheleznov.dto.SignInForm;

public interface SignInService {
    boolean authenticate(SignInForm form);
}
