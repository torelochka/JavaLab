package ru.itis.zheleznov.models;

import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
public class User {
    private Long id;
    private String name;
    private String surname;
    private String email;
}

