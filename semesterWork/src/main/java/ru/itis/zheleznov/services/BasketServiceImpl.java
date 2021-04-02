package ru.itis.zheleznov.services;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.itis.zheleznov.models.Basket;
import ru.itis.zheleznov.models.Product;
import ru.itis.zheleznov.models.User;
import ru.itis.zheleznov.repositories.BasketRepository;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class BasketServiceImpl implements BasketService {

    private final BasketRepository basketRepository;

    public BasketServiceImpl(BasketRepository basketRepository) {
        this.basketRepository = basketRepository;
    }

    @Override
    public Optional<Basket> getUserBasket(User user) {
        return basketRepository.findByUser(user);
    }

    @Override
    public Basket addProductInBasket(Basket basket, Product product) {
        basket.getProducts().add(product);
        return basketRepository.save(basket);
    }

    @Override
    public Basket createOrGetBasket(User user) {
        Optional<Basket> MaybeBasket = getUserBasket(user);
        if (!MaybeBasket.isPresent()) {
            Basket basket = Basket.builder().products(new ArrayList<>()).user(user).build();
            return save(basket);
        }
        return MaybeBasket.get();
    }

    @Override
    public Basket save(Basket basket) {
        return basketRepository.save(basket);
    }

    @Override
    public Basket deleteProductFromBasket(Basket basket, Product product) {
        basket.getProducts().remove(product);
        return basketRepository.save(basket);
    }
}
