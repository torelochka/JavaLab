package ru.itis.zheleznov.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.itis.zheleznov.models.User;

import java.util.List;
import java.util.Optional;

@Repository
public class UserRepositoryJdbcTemplateImpl implements UserRepository {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    //language=SQL
    private static final String SQL_FIND_ALL = "SELECT * FROM users";

    //language=SQL
    private static final String SQL_FIND_BY_ID = "SELECT * FROM users where id=:id";

    //language=SQL
    private static final String SQL_FIND_BY_EMAIL = "SELECT * FROM users where email=:email";

    //language=SQL
    private static final String SQL_FIND_BY_EMAIl_HASH =
            "SELECT * FROM users where email=:email AND hash_password=:hash";

    //language=SQL
    private static final String SQL_SAVE = "INSERT INTO users (name, email, lastname, hash_password) values (:name, :email, :lastname, :hash);";

    private final RowMapper<User> userRowMapper = (row, i) -> User.builder()
            .id(row.getLong("id"))
            .name(row.getString("name"))
            .lastname(row.getString("lastname"))
            .hashPassword(row.getString("hash_password"))
            .email(row.getString("email"))
            .rights(User.Right.valueOf(row.getString("rights")))
            .build();


    @Override
    public boolean save(User entity) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("name", entity.getName());
        parameterSource.addValue("lastname", entity.getLastname());
        parameterSource.addValue("email", entity.getEmail());
        parameterSource.addValue("hash", entity.getHashPassword());
        return namedParameterJdbcTemplate.update(SQL_SAVE, parameterSource) > 0;
    }

    @Override
    public boolean update(User entity) {
        return false;
    }

    @Override
    public List<User> getAll() {
        return namedParameterJdbcTemplate.query(SQL_FIND_ALL, userRowMapper);
    }

    @Override
    public Optional<User> getById(long id) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("id", id);
        try {
            return Optional.ofNullable(namedParameterJdbcTemplate.queryForObject(SQL_FIND_BY_ID, parameterSource, userRowMapper));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public boolean delete(User entity) {
        return false;
    }

    @Override
    public Optional<User> getUser(String email, String hash) {
        MapSqlParameterSource sqlParameterSource = new MapSqlParameterSource();
        sqlParameterSource.addValue("email", email);
        sqlParameterSource.addValue("hash", hash);
        try {
            return Optional.ofNullable(namedParameterJdbcTemplate.queryForObject(SQL_FIND_BY_EMAIl_HASH, sqlParameterSource, userRowMapper));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<User> getUser(String email) {
        MapSqlParameterSource sqlParameterSource = new MapSqlParameterSource();
        sqlParameterSource.addValue("email", email);
        try {
            return Optional.ofNullable(namedParameterJdbcTemplate.queryForObject(SQL_FIND_BY_EMAIL, sqlParameterSource, userRowMapper));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }
}
