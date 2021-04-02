package ru.itis.zheleznov.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.InputStream;
import java.io.Serializable;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String firstname;
    private String lastname;
    private String email;
    @Column(name = "password_hash")
    private String passwordHash;
    @Column(name = "confirmed_code")
    private UUID confirmedCode;

    @Builder.Default
    private String image = "/views/assets/user/profile.png";
    @Builder.Default
    @Enumerated(value = EnumType.STRING)
    private Status status = Status.NOT_CONFIRMED;

    @Builder.Default
    @Enumerated(value = EnumType.STRING)
    private State state = State.ACTIVE;

    @Builder.Default
    @Enumerated(value = EnumType.STRING)
    private Role role = Role.USER;

    public enum State {
        ACTIVE, BANNED
    }

    public enum Role {
        USER, ADMIN
    }

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

    public boolean isActive() {
        return this.state == State.ACTIVE;
    }

    public boolean isBanned() {
        return this.state == State.BANNED;
    }

    public boolean isAdmin() {
        return this.role == Role.ADMIN;
    }
}
