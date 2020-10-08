package ru.itis.zheleznov.repositories;

import ru.itis.zheleznov.models.User;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UsersRepositoryJdbcImpl implements UsersRepository {

    private DataSource dataSource;

    //language=SQL
    private static final String SQL_FIND_ALL = "select * from users";

    //language=SQL
    private static final String SQL_FIND_ALL_BY_NAME = "select * from users where name = ?";

    private RowMapper<User> userRowMapper = row -> User.builder()
            .id(row.getLong("id"))
            .name(row.getString("name"))
            .surname(row.getString("surname"))
            .email(row.getString("email"))
            .build();


    public UsersRepositoryJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<User> findAllByName(String name) {
        SimpleJdbcTemplate simpleJdbcTemplate = new SimpleJdbcTemplate(dataSource);
        return simpleJdbcTemplate.query(SQL_FIND_ALL_BY_NAME, userRowMapper, name);
    }

    @Override
    public void save(User entity) {

    }

    @Override
    public void update(User entity) {

    }

    @Override
    public void delete(User entity) {

    }

    @Override
    public Optional<User> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<User> findAll() {
        SimpleJdbcTemplate simpleJdbcTemplate = new SimpleJdbcTemplate(dataSource);
        return simpleJdbcTemplate.query(SQL_FIND_ALL, userRowMapper);
    }

    @Override
    public List<User> findAllByAge(int age) {
        return null;
    }
}
