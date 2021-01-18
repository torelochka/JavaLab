package ru.itis.zheleznov.repositories;

import ru.itis.zheleznov.models.Product;
import ru.itis.zheleznov.models.Purchase;
import ru.itis.zheleznov.models.User;

import java.util.List;

public interface PurchaseRepository {
    void save(Purchase purchase);

    List<Product> userPurchase(User user);
}
