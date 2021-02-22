package ru.itis.zheleznov.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.InputStream;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    private long id;
    private String name;
    private String lastname;
    private String email;
    private String passwordHash;
    private InputStream profileImg;
    private UUID confirmedCode;
    @Builder.Default
    private Right rights = Right.USER;
    @Builder.Default
    private String image = "/views/assets/user/profile.png";
    @Builder.Default
    private Status status = Status.NOT_CONFIRMED;

    public enum Status {
        CONFIRMED("CONFIRMED"),
        NOT_CONFIRMED("NOT_CONFIRMED");

        private final String string;
        Status(String status) {
            this.string = status;
        }
        public String getString() {
            return string;
        }
    }

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
