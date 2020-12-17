package ru.itis.zheleznov.services;

import ru.itis.zheleznov.dto.CategoryDto;

import java.util.List;

public interface CategoryService {
    List<CategoryDto> getAllCategories();
}
