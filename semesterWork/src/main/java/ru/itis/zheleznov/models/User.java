package ru.itis.zheleznov.models;


import lombok.*;

@Data
@Builder
public class User {

    private long id;
    private String name;
    private String lastname;
    private String email;
    private String hashPassword;
    @Builder.Default
    private Right rights = Right.USER;

    public enum Right {
        ADMIN("ADMIN"),
        USER("USER"),
        UNKNOWN("UNKNOWN");

        private final String string;

        Right(String rights) {
            this.string = rights;
        }

        public String getString() {
            return string;
        }
    }
}

