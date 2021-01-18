package ru.itis.zheleznov.repositories;

import org.springframework.stereotype.Repository;
import ru.itis.zheleznov.models.Category;

import java.util.List;

@Repository
public interface CategoryRepository {
    List<Category> getAll();
}
