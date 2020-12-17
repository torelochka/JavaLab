package ru.itis.zheleznov.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.zheleznov.models.Category;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryDto {
    private String name;
    private String image;

    public static CategoryDto from(Category category) {
        if (category == null) {
            return null;
        }

        return CategoryDto.builder()
                .name(category.getName())
                .image(category.getImage())
                .build();
    }

    public static List<CategoryDto> from(List<Category> categories) {
        return categories.stream()
                .map(CategoryDto::from)
                .collect(Collectors.toList());
    }
}
