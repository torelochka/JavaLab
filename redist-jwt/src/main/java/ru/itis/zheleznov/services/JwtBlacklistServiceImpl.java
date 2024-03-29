package ru.itis.zheleznov.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.zheleznov.models.Blacklist;
import ru.itis.zheleznov.repositories.BlacklistRepository;

@Service
public class JwtBlacklistServiceImpl implements JwtBlacklistService {

    @Autowired
    private BlacklistRepository blacklistRepository;

    @Override
    public void add(String token) {
        Blacklist blacklist = Blacklist.builder()
                .token(token)
                .build();

        blacklistRepository.save(blacklist);
    }

    @Override
    public boolean exists(String token) {
        return blacklistRepository.findById(token).isPresent();
    }
}
