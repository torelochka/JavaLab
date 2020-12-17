package ru.itis.zheleznov.repositories;

import ru.itis.zheleznov.models.Category;

import java.util.List;

public interface CategoryRepository {
    List<Category> getAll();
}

