package ru.itis.zheleznov.services;

import org.springframework.stereotype.Service;
import ru.itis.zheleznov.models.Product;
import ru.itis.zheleznov.models.SearchRequest;
import ru.itis.zheleznov.repositories.ProductRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceJdbcImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceJdbcImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> allProducts() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> getProductsBySearchRequest(SearchRequest searchRequest) {
        String filter = searchRequest.getFilter();
        String input = searchRequest.getInput();

        List<Product> productList = new ArrayList<>();
        if (input != null) {
            if (filter != null) {
                if (filter.equals("price")) {
                    productList = productRepository.getProductsByNameOrderByPrice(input);
                } else if (filter.equals("popular")) {
                    productList = productRepository.getProductsByNameOrderByPopular(input);
                }
            } else {
                productList = productRepository.getProductsByName(input);
            }
        } else {
            productList = allProducts();
        }

        return productList;
    }

    @Override
    public Product getProductById(long id) {
        return productRepository.getProductById(id).orElse(null);
    }
}
