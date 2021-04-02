package ru.itis.zheleznov.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.zheleznov.models.Category;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryDto {
    private Long id;
    private String name;
    private String image;


    public static CategoryDto from(Category category) {
        return CategoryDto.builder()
                .id(category.getId())
                .name(category.getName())
                .image(category.getImage())
                .build();
    }

    public static List<CategoryDto> from(List<Category> categories) {
        return categories.stream().map(CategoryDto::from).collect(Collectors.toList());
    }

}
