package ru.itis.zheleznov.repositories;

import org.springframework.data.keyvalue.repository.KeyValueRepository;
import org.springframework.stereotype.Repository;
import ru.itis.zheleznov.models.RedisUser;

@Repository
public interface RedisUserRepository extends KeyValueRepository<RedisUser, String> {

}
