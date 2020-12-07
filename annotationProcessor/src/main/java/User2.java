package model;

import annotations.HtmlForm;
import annotations.HtmlInput;

/**
 * 03.12.2020
 * 15.Annotations_SOURCE
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
@HtmlForm(method = "get", action = "/test")
public class User2 {
    @HtmlInput(name = "name", placeholder = "Ваш супер ник")
    private String nickname;
    @HtmlInput(name = "mail",type = "email", placeholder = "Ваша почта")
    private String email;
    @HtmlInput(name = "password", type = "password", placeholder = "Ваш секретный Пароль")
    private String password;
}
