package ru.itis.zheleznov.repositories;

import org.springframework.data.keyvalue.repository.KeyValueRepository;
import org.springframework.stereotype.Repository;
import ru.itis.zheleznov.models.Blacklist;
import ru.itis.zheleznov.models.RedisUser;

import java.util.Optional;

@Repository
public interface BlacklistRepository extends KeyValueRepository<Blacklist, String> {
    Optional<Blacklist> findByToken(String token);
}

