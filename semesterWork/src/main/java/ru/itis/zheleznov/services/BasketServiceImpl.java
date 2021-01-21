package ru.itis.zheleznov.services;

import org.springframework.stereotype.Service;
import ru.itis.zheleznov.models.Basket;
import ru.itis.zheleznov.models.Product;
import ru.itis.zheleznov.models.User;
import ru.itis.zheleznov.repositories.BasketRepository;

import java.util.ArrayList;

@Service
public class BasketServiceImpl implements BasketService {

    private final BasketRepository basketRepository;

    public BasketServiceImpl(BasketRepository basketRepository) {
        this.basketRepository = basketRepository;
    }

    @Override
    public void createBasket(Basket basket) {
        basketRepository.save(basket);
    }

    @Override
    public Basket getUserBasket(User user) {
        return basketRepository.getUserBasket(user).orElse(null);
    }

    @Override
    public void addProductInBasket(Basket basket, Product product) {
        basketRepository.addProduct(basket, product);
    }

    public Basket createOrGetBasket(User user) {
        Basket basket = getUserBasket(user);
        if (basket == null) {
            basket = Basket.builder().products(new ArrayList<>()).user(user).build();
            createBasket(basket);
        }
        return basket;
    }

    @Override
    public void deleteProductFromBasket(Basket basket, Product product) {
        basketRepository.deleteProduct(basket, product);
    }
}
