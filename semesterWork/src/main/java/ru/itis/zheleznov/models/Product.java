package ru.itis.zheleznov.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {
    private int id;
    private String name;
    private String description;
    private int price;
    private Category category;
    @Builder.Default
    private String image = ".";
    @Builder.Default
    private int popularity = 0;
}
