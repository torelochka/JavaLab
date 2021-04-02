package ru.itis.zheleznov.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itis.zheleznov.models.Basket;
import ru.itis.zheleznov.models.Product;
import ru.itis.zheleznov.models.User;

import java.util.Optional;

@Repository
public interface BasketRepository extends JpaRepository<Basket, Long> {
    Optional<Basket> findByUser(User user);
}
