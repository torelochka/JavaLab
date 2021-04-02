package ru.itis.zheleznov.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Basket {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToMany(fetch = FetchType.EAGER)
    private List<Product> products;
    @JoinColumn(name = "user_id")
    @OneToOne
    private User user;
}
