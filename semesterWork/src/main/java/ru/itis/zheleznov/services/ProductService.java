package ru.itis.zheleznov.services;

import org.springframework.stereotype.Service;
import ru.itis.zheleznov.models.Product;
import ru.itis.zheleznov.dto.SearchRequest;

import java.util.List;

@Service
public interface ProductService {
    List<Product> allProducts();

    List<Product> getProductsBySearchRequest(SearchRequest searchRequest);

    Product getProductById(long id);
}
