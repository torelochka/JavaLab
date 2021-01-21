package ru.itis.zheleznov.repositories;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ru.itis.zheleznov.models.Basket;
import ru.itis.zheleznov.models.Product;
import ru.itis.zheleznov.models.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class BasketRepositoryJdbcImpl implements BasketRepository {

    private final JdbcTemplate template;

    private UserRepository userRepository;

    //language=SQL
    private static final String SQL_CREATE = "INSERT INTO basket (customer_id) VALUES (?);";

    //language=SQL
    private static final String SQL_ADD_PRODUCT = "INSERT INTO customer_basket (basket_id, product_id) VALUES (?, ?);";

    //language=SQL
    private static final String SQL_DELETE_PRODUCT = "DELETE FROM customer_basket WHERE basket_id=? AND product_id=?";

    //language=SQL
    private static final String SQL_GET_PRODUCTS = "SELECT p.id, p.popularity, p.name, p.description, p.price, p.image, c.name as ca_name FROM customer_basket inner join basket b on b.id = customer_basket.basket_id inner join product p on p.id = customer_basket.product_id inner join categories c on c.id = p.category_id where basket_id=?;";

    //language=SQL
    private static final String SQL_FIND_BY_USER_ID = "SELECT * FROM basket where customer_id=?";

    public RowMapper<Basket> basketRowMapper = (row, i) -> Basket.builder()
            .id(row.getInt("id"))
            .user(userRepository.findById(row.getInt("customer_id")).get())
            .products(productsInBasket(row.getInt("id")))
            .build();

    public static RowMapper<Product> productRowMapper = ProductRowMapper.productRowMapper;

    private List<Product> productsInBasket(int id) {
        return template.query(SQL_GET_PRODUCTS, productRowMapper, id);
    }

    public BasketRepositoryJdbcImpl(JdbcTemplate template, UserRepository userRepository) {
        this.template = template;
        this.userRepository = userRepository;
    }

    @Override
    public Optional<Basket> getUserBasket(User user) {
        try {
            return Optional.ofNullable(template.queryForObject(SQL_FIND_BY_USER_ID, basketRowMapper, user.getId()));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public void save(Basket basket) {
        template.update(SQL_CREATE, basket.getUser().getId());
    }

    @Override
    public void addProduct(Basket basket, Product product) {
        template.update(SQL_ADD_PRODUCT, basket.getId(), product.getId());
    }

    @Override
    public void deleteProduct(Basket basket, Product product) {
        template.update(SQL_DELETE_PRODUCT, basket.getId(), product.getId());
    }
}
