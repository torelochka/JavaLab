package ru.itis.zheleznov.services;

public interface JwtBlacklistService {
    void add(String token);

    boolean exists(String token);
}
