package ru.itis.zheleznov.services;

import ru.itis.zheleznov.models.Basket;
import ru.itis.zheleznov.models.Product;
import ru.itis.zheleznov.models.User;

import java.util.Optional;

public interface BasketService {
    Basket save(Basket basket);

    Basket createOrGetBasket(User user);

    Optional<Basket> getUserBasket(User user);

    Basket addProductInBasket(Basket basket, Product product);

    Basket deleteProductFromBasket(Basket basket, Product product);
}
