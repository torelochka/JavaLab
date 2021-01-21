package ru.itis.zheleznov.repositories;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.itis.zheleznov.models.Category;

import java.util.List;

@Repository
public class CategoryRepositoryJdbcImpl implements CategoryRepository {

    private final JdbcTemplate template;

    //language=SQL
    private final static String SQL_FIND_ALL = "SELECT * FROM categories";

    private final RowMapper<Category> categoryRowMapper = (row, i) -> Category.builder()
            .name(row.getString("name"))
            .image(row.getString("image"))
            .build();

    public CategoryRepositoryJdbcImpl(JdbcTemplate template) {
        this.template = template;
    }

    @Override
    public List<Category> getAll() {
        return template.query(SQL_FIND_ALL, categoryRowMapper);
    }
}
