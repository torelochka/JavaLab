package ru.itis.zheleznov.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.zheleznov.models.Product;
import ru.itis.zheleznov.models.Purchase;
import ru.itis.zheleznov.models.User;

import java.util.List;

public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
    List<Purchase> findPurchaseByCustomer(User user);
}
