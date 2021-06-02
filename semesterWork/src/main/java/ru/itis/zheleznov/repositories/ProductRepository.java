package ru.itis.zheleznov.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itis.zheleznov.models.Product;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByNameContaining(String name);

    List<Product> findByNameContainingOrderByPrice(String name);

    List<Product> findByNameContainingOrderByPopularityDesc(String name);
}
