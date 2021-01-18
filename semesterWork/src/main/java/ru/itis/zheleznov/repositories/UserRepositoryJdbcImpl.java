package ru.itis.zheleznov.repositories;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ru.itis.zheleznov.models.User;

import java.io.ByteArrayInputStream;
import java.util.List;
import java.util.Optional;

@Repository
public class UserRepositoryJdbcImpl implements UserRepository {

    private JdbcTemplate template;

    //language=SQL
    private static final String SQL_SAVE = "INSERT INTO users (name, email, lastname, hash_password, rights)" +
            "VALUES (?, ?, ?, ?, ?);";

    //language=SQL
    private final String SQL_FIND_ID = "SELECT * FROM users where id=?";

    //language=SQL
    private final String SQL_FIND_EMAIL = "SELECT * FROM users where email=?";

    //language=SQL
    private final String SQL_FIND_EMAIL_PASSWORD = "SELECT * FROM users where email=? and hash_password=?";

    //language=SQL
    private final String SQL_FIND_ALL = "SELECT * FROM users";

    public UserRepositoryJdbcImpl(JdbcTemplate template) {
        this.template = template;
    }

    private final RowMapper<User> userRowMapper = (row, i) -> User.builder()
            .id(row.getLong("id"))
            .name(row.getString("name"))
            .lastname(row.getString("lastname"))
            .email(row.getString("email"))
            .passwordHash(row.getString("hash_password"))
            .rights(User.Right.valueOf(row.getString("rights")))
            .build();

    @Override
    public void save(User user) {
        template.update(SQL_SAVE, user.getName(), user.getEmail(), user.getLastname(), user.getPasswordHash(), user.getRights().getString());
    }

    @Override
    public Optional<User> findByEmailPassword(String email, String password) {
        return Optional.ofNullable(template.queryForObject(SQL_FIND_EMAIL_PASSWORD, userRowMapper, email, password));
    }

    @Override
    public Optional<User> findByEmail(String email) {
        try {
            return Optional.ofNullable(template.queryForObject(SQL_FIND_EMAIL, userRowMapper, email));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<User> findById(long id) {
        return Optional.ofNullable(template.queryForObject(SQL_FIND_ID, userRowMapper, id));
    }

    @Override
    public List<User> findAll() {
        return template.query(SQL_FIND_ALL, userRowMapper);
    }
}
