package ru.itis.zheleznov.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import ru.itis.zheleznov.models.User;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

public class UserRepositoryJdbcImpl implements UserRepository {

    private final JdbcTemplate jdbcTemplate;

    //language=SQL
    private final static String SQL_FIND_ALL = "SELECT * FROM users";

    //language=SQL
    private final static String SQL_FIND_BY_ID = "SELECT * FROM users where id=?";

    //language=SQL
    private final static String SQL_FIND_BY_EMAIL = "SELECT * FROM users where email=?";

    //language=SQL
    private final static String SQL_SAVE = "INSERT INTO users (email, hash) VALUES (?, ?)";

    //language=SQL
    private static final String SQL_UPDATE = "UPDATE users set email=?, hash=?, is_deleted=? WHERE email=?";

    //language=SQL
    private static final String SQL_DELETE = "UPDATE users set is_deleted=FALSE WHERE email=?";

    public UserRepositoryJdbcImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private final RowMapper<User> userRowMapper = (row, i) -> User.builder()
            .email(row.getString("email"))
            .hash(row.getString("hash"))
            .deleted(row.getBoolean("is_deleted"))
            .build();

    @Override
    public List<User> getAll() {
        return jdbcTemplate.query(SQL_FIND_ALL, userRowMapper);
    }

    @Override
    public Optional<User> getById(long id) {
        return Optional.ofNullable(jdbcTemplate.queryForObject(SQL_FIND_BY_ID, userRowMapper, id));
    }

    @Override
    public Optional<User> getByEmail(String email) {
        return Optional.ofNullable(jdbcTemplate.queryForObject(SQL_FIND_BY_EMAIL, userRowMapper, email));
    }

    @Override
    public void save(User user) {
        jdbcTemplate.update(SQL_SAVE, user.getEmail(), user.getHash());
    }

    @Override
    public void update(User user) {
        jdbcTemplate.update(SQL_UPDATE, userRowMapper, user.getEmail(), user.getHash(), user.isDeleted());
    }

    @Override
    public void delete(User user) {
        jdbcTemplate.update(SQL_DELETE, userRowMapper, user.getEmail());
    }
}
