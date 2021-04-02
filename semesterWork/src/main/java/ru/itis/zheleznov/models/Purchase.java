package ru.itis.zheleznov.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne()
    @JoinColumn(name = "basket_id")
    private Basket basket;
    @ManyToOne()
    @JoinColumn(name = "user_id")
    private User customer;
    private String date;
    @OneToMany()
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Product> products;
}
