package ru.itis.zheleznov.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.InputStream;

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
    @Builder.Default
    private Right rights = Right.USER;
    @Builder.Default
    private String image = "/views/assets/user/profile.png";
    /*public String getMesImage() {
        return FileToImage.toImage(profileImg);
    }*/

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
