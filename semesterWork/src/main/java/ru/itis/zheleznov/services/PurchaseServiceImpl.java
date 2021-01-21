package ru.itis.zheleznov.services;

import org.springframework.stereotype.Service;
import ru.itis.zheleznov.models.Product;
import ru.itis.zheleznov.models.Purchase;
import ru.itis.zheleznov.models.User;
import ru.itis.zheleznov.repositories.PurchaseRepository;

import java.util.List;

@Service
public class PurchaseServiceImpl implements PurchaseService {

    private final PurchaseRepository purchaseRepository;

    public PurchaseServiceImpl(PurchaseRepository purchaseRepository) {
        this.purchaseRepository = purchaseRepository;
    }

    @Override
    public List<Product> getUserPurchase(User user) {
        return purchaseRepository.userPurchase(user);
    }

    @Override
    public void addPurchase(Purchase purchase) {
        purchaseRepository.save(purchase);
    }


}
