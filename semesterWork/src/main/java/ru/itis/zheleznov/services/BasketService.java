package ru.itis.zheleznov.services;

import ru.itis.zheleznov.models.Basket;
import ru.itis.zheleznov.models.Product;
import ru.itis.zheleznov.models.User;

public interface BasketService {
    void createBasket(Basket basket);

    Basket createOrGetBasket(User user);

    Basket getUserBasket(User user);

    void addProductInBasket(Basket basket, Product product);

    void deleteProductFromBasket(Basket basket, Product product);
}
