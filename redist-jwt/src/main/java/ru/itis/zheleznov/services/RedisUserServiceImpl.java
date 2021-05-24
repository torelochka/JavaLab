package ru.itis.zheleznov.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.zheleznov.models.RedisUser;
import ru.itis.zheleznov.models.User;
import ru.itis.zheleznov.repositories.RedisUserRepository;
import ru.itis.zheleznov.repositories.UserRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class RedisUserServiceImpl implements RedisUserService {

    @Autowired
    private UserRepository usersRepository;

    @Autowired
    private JwtBlacklistService blacklistService;

    @Autowired
    private RedisUserRepository redisUsersRepository;

    @Override
    public void addTokenToUser(User user, String token) {
        String redisId = user.getRedisId();

        RedisUser redisUser;
        if (redisId != null) {
            redisUser = redisUsersRepository.findById(redisId).orElseThrow(IllegalArgumentException::new);
            if (redisUser.getTokens() == null) {
                redisUser.setTokens(new ArrayList<>());
            }
            redisUser.getTokens().add(token);
        } else {
            redisUser = RedisUser.builder()
                    .userId(user.getId())
                    .tokens(Collections.singletonList(token))
                    .build();
        }
        redisUsersRepository.save(redisUser);
        user.setRedisId(redisUser.getId());
        usersRepository.save(user);
    }

    @Override
    public void addAllTokensToBlackList(User user) {
        if (user.getRedisId() != null) {
            RedisUser redisUser = redisUsersRepository.findById(user.getRedisId())
                    .orElseThrow(IllegalArgumentException::new);

            List<String> tokens = redisUser.getTokens();
            for (String token : tokens) {
                blacklistService.add(token);
            }
            redisUser.getTokens().clear();
            redisUsersRepository.save(redisUser);
        }
    }
}
