package ru.itis.zheleznov.services;

import org.springframework.stereotype.Service;
import ru.itis.zheleznov.models.Product;
import ru.itis.zheleznov.models.Purchase;
import ru.itis.zheleznov.models.User;
import ru.itis.zheleznov.repositories.PurchaseRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class PurchaseServiceImpl implements PurchaseService {

    private final PurchaseRepository purchaseRepository;

    public PurchaseServiceImpl(PurchaseRepository purchaseRepository) {
        this.purchaseRepository = purchaseRepository;
    }

    @Override
    public List<Purchase> getUserPurchase(User user) {
        List<Purchase> purchaseByUserId = purchaseRepository.findPurchaseByCustomer(user);
        System.out.println(purchaseByUserId);
        return purchaseByUserId;
    }

    @Override
    public void save(Purchase purchase) {
        purchaseRepository.save(purchase);
    }


}
