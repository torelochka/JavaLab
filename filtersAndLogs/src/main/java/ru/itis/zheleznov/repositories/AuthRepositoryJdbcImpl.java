package ru.itis.zheleznov.repositories;

import javax.servlet.http.Cookie;
import javax.sql.DataSource;
import java.util.List;
import java.util.UUID;

public class AuthRepositoryJdbcImpl implements AuthRepository {

    private final DataSource dataSource;
    private final SimpleJdbcTemplate template;

    //language=SQL
    private final String SQL_CREATE = "insert into auth (value) values (?);";

    //language=SQL
    private final String SQL_FIND = "select * from auth where value=?;";

    public AuthRepositoryJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
        template = new SimpleJdbcTemplate(dataSource);
    }

    public RowMapper<Cookie> cookieRowMapper = row -> new Cookie("AUTH", row.getString("value"));

    @Override
    public void create(Cookie cookie) {
        template.update(SQL_CREATE, cookie.getValue());
    }

    @Override
    public boolean find(Cookie cookie) {
        List<Cookie> cookies = template.query(SQL_FIND, cookieRowMapper, cookie.getValue());
        return !cookies.isEmpty();
    }
}
