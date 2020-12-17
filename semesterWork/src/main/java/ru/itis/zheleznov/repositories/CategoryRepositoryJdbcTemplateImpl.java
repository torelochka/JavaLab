package ru.itis.zheleznov.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ru.itis.zheleznov.models.Category;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class CategoryRepositoryJdbcTemplateImpl implements CategoryRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    //language=SQL
    private static final String SQL_FIND_ALL = "SELECT * FROM categories;";

    private final RowMapper<Category> categoryRowMapper = (row, i) -> Category.builder()
            .id(row.getLong("id"))
            .name(row.getString("name"))
            .image(row.getString("image"))
            .build();

    @Override
    public List<Category> getAll() {
        return jdbcTemplate.query(SQL_FIND_ALL, categoryRowMapper);
    }
}
