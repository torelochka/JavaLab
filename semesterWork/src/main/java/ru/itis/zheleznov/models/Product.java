package ru.itis.zheleznov.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    @Type(type = "text")
    private String description;
    private Integer price;
    @ManyToOne()
    @JoinColumn(name = "category_id")
    private Category category;
    @Builder.Default
    private String image = ".";
    @Builder.Default
    private Long popularity = 0L;
}
