package ru.itis.zheleznov.repositories;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ru.itis.zheleznov.models.*;

import java.util.List;
import java.util.Optional;

@Repository
public class PurchaseRepositoryJdbcImpl implements PurchaseRepository {

    private final JdbcTemplate template;
    private final ProductRepository productRepository;
    private final BasketRepository basketRepository;

    //language=SQL
    private static final String SQL_FIND = "SELECT p.id, p.popularity, p.name, p.description, p.price, p.image, c.name as ca_name FROM purchase pu inner join customer_purchase cp on pu.id = cp.purchase_id inner join product p on p.id = cp.product_id inner join categories c on c.id = p.category_id where customer_id=?";

    //language=SQL
    private static final String SQL_SAVE = "INSERT INTO purchase (basket_id, date, customer_id) VALUES (?, 'today'::date, ?);";

    //language=SQL
    private static final String SQL_SAVE_PRODUCTS = "INSERT INTO customer_purchase (purchase_id, product_id) VALUES (?, ?);";

    //language=SQL
    private static final String SQL_FIND_ID = "SELECT id from purchase where basket_id=? ORDER BY id DESC;";

    private final RowMapper<Purchase> purchaseRowMapper = (row, id) -> Purchase.builder()
            .id(row.getInt("id")).build();

    public static RowMapper<Product> productRowMapper = ProductRowMapper.productRowMapper;

    public PurchaseRepositoryJdbcImpl(JdbcTemplate template, ProductRepository productRepository, BasketRepository basketRepository) {
        this.template = template;
        this.productRepository = productRepository;
        this.basketRepository = basketRepository;
    }

    @Override
    public void save(Purchase entity) {
        template.update(SQL_SAVE, entity.getBasketId(), entity.getCustomer().getId());
        Optional<Purchase> purchase = Optional.ofNullable(template.query(SQL_FIND_ID, purchaseRowMapper, entity.getBasketId()).get(0));
        if (purchase.isPresent()) {
            for (Product product : entity.getProducts()) {
                product.setPopularity(product.getPopularity() + 1);
                template.update(SQL_SAVE_PRODUCTS, purchase.get().getId(), product.getId());
                basketRepository.deleteProduct(Basket.builder().id(entity.getBasketId()).build(), product);
                productRepository.update(product);
            }
        }
    }

    @Override
    public List<Product> userPurchase(User user) {
        return template.query(SQL_FIND, productRowMapper, user.getId());
    }
}
