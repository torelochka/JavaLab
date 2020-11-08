package ru.itis.zheleznov;

import ru.itis.zheleznov.models.User;

import javax.sql.DataSource;
import java.util.UUID;

public class Main {

    public static void main(String[] args) {
        DataSource dataSource = SimpleDataSource.getDataSource();
        EntityManager entityManager = new EntityManager(dataSource);
        entityManager.dropTable("users");
        entityManager.createTable("users", User.class);
        UUID id = UUID.randomUUID();
        entityManager.safe("users", User.builder()
                .id(id)
                .email("test")
                .firstName("first")
                .lastName("last")
                .hash(false).build());
        System.out.println(entityManager.findById("users", User.class, UUID.randomUUID()));
    }
}
