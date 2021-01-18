package ru.itis.zheleznov.repositories;

import ru.itis.zheleznov.models.Basket;
import ru.itis.zheleznov.models.Product;
import ru.itis.zheleznov.models.User;

import java.util.Optional;

public interface BasketRepository {
    Optional<Basket> getUserBasket(User user);

    void save(Basket basket);

    void addProduct(Basket basket, Product product);

    void deleteProduct(Basket basket, Product product);
}
