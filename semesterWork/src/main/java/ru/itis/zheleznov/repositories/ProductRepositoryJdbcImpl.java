package ru.itis.zheleznov.repositories;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ru.itis.zheleznov.models.Category;
import ru.itis.zheleznov.models.Product;

import java.util.List;
import java.util.Optional;

@Repository
public class ProductRepositoryJdbcImpl implements ProductRepository {

    //language=SQL
    private final String SQL_FIND_ALL = "SELECT p.id, p.name, p.popularity, p.description, p.price, p.image, c.name ca_name FROM product p INNER JOIN categories c on c.id = p.category_id";

    //language=SQL
    private final String SQL_UPDATE = "update product set popularity = ? where id=?";

    //language=SQL
    private final String SQL_FIND_BY_ID = "SELECT p.id, p.name, p.description, p.price, p.popularity, p.image, c.name ca_name FROM product p INNER JOIN categories c on c.id = p.category_id where p.id=?";

    //language=SQL
    private final String SQL_FIND_BY_NAME = "SELECT p.id, p.name, p.description, p.price, p.image, p.popularity, c.name ca_name FROM product p INNER JOIN categories c on c.id = p.category_id where p.name LIKE ?";

    //language=SQL
    private final String SQL_FIND_BY_NAME_ORDER_BY_PRICE = SQL_FIND_BY_NAME + " ORDER BY price;";

    //language=SQL
    private final String SQL_FIND_BY_NAME_ORDER_BY_POPULARITY = SQL_FIND_BY_NAME + " ORDER BY popularity DESC;";

    private final JdbcTemplate template;

    public ProductRepositoryJdbcImpl(JdbcTemplate template) {
        this.template = template;
    }

    public static RowMapper<Product> productRowMapper = ProductRowMapper.productRowMapper;

    @Override
    public void update(Product entity) {
        template.update(SQL_UPDATE, entity.getPopularity(), entity.getId());
    }

    @Override
    public Optional<Product> getProductById(long id) {
        return Optional.ofNullable(template.queryForObject(SQL_FIND_BY_ID, productRowMapper, id));
    }

    @Override
    public List<Product> findAll() {
        return template.query(SQL_FIND_ALL, productRowMapper);
    }

    @Override
    public List<Product> getProductsByName(String name) {
        return template.query(SQL_FIND_BY_NAME, productRowMapper, searchRequest(name));
    }

    @Override
    public List<Product> getProductsByNameOrderByPrice(String name) {
        return template.query(SQL_FIND_BY_NAME_ORDER_BY_PRICE, productRowMapper, searchRequest(name));
    }

    @Override
    public List<Product> getProductsByNameOrderByPopular(String name) {
        return template.query(SQL_FIND_BY_NAME_ORDER_BY_POPULARITY, productRowMapper, searchRequest(name));
    }

    private String searchRequest(String name) {
        return "%" + name + "%";
    }
}
