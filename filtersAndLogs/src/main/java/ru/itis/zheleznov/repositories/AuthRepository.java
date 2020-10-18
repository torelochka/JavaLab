package ru.itis.zheleznov.repositories;

import javax.servlet.http.Cookie;
import java.util.UUID;

public interface AuthRepository {

    void create(Cookie cookie);
    boolean find(Cookie cookie);
}
