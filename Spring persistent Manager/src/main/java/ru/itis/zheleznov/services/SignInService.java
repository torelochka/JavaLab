package ru.itis.zheleznov.services;

import ru.itis.zheleznov.models.SignInForm;

public interface SignInService {
    boolean authenticate(SignInForm form);
}
