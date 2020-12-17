package ru.itis.zheleznov.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.zheleznov.dto.CategoryDto;
import ru.itis.zheleznov.repositories.CategoryRepository;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;


    @Override
    public List<CategoryDto> getAllCategories() {
        return CategoryDto.from(categoryRepository.getAll());
    }
}
