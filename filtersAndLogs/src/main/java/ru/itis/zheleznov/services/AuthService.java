package ru.itis.zheleznov.services;

import javax.servlet.http.Cookie;
import java.util.UUID;

public interface AuthService {
    void addCookieAuth(Cookie cookie);
    boolean findCookieAuth(Cookie cookie);
}
