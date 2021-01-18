package ru.itis.zheleznov.repositories;

import ru.itis.zheleznov.models.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    void update(Product product);

    Optional<Product> getProductById(long id);

    List<Product> findAll();

    List<Product> getProductsByName(String name);

    List<Product> getProductsByNameOrderByPrice(String name);

    List<Product> getProductsByNameOrderByPopular(String name);
}
