package ru.itis.zheleznov.services;

import ru.itis.zheleznov.models.Product;
import ru.itis.zheleznov.models.Purchase;
import ru.itis.zheleznov.models.User;

import java.util.List;

public interface PurchaseService {
    List<Product> getUserPurchase(User user);

    void addPurchase(Purchase purchase);
}
