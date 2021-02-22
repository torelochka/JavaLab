package ru.itis.zheleznov.utils;

public interface MailsGenerator {
    String getMailForConfirm(String serverUrl, String confirmedCode);
}
