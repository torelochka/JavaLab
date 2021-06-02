package ru.itis.zheleznov.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.zheleznov.models.Product;
import ru.itis.zheleznov.models.User;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BasketDto {

    private Long id;
    private List<ProductDto> products;
    private UserDto user;
}
