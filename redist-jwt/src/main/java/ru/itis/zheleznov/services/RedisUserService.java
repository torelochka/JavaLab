package ru.itis.zheleznov.services;

import ru.itis.zheleznov.models.User;

public interface RedisUserService {
    void addTokenToUser(User user, String token);

    void addAllTokensToBlackList(User user);
}
