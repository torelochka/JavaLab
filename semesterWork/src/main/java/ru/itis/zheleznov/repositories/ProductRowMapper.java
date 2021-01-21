package ru.itis.zheleznov.repositories;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.itis.zheleznov.models.Category;
import ru.itis.zheleznov.models.Product;

@Component
public class ProductRowMapper {

    public static RowMapper<Product> productRowMapper = (row, i) -> Product.builder()
            .id(row.getInt("id"))
            .name(row.getString("name"))
            .description(row.getString("description"))
            .price(row.getInt("price"))
            .image(row.getString("image"))
            .category(Category.builder().name(row.getString("ca_name")).build())
            .popularity(row.getInt("popularity"))
            .build();
}
