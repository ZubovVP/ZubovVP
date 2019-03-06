package ru.job4j.storage;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Encryption {
    private MessageDigest md5;
    private static Encryption ourInstance = new Encryption();

    public static Encryption getInstance() {
        return ourInstance;
    }

    private Encryption() {
        try {
            this.md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    public String encrypt(String element) {
        StringBuilder builder = new StringBuilder();
        byte[] bytes = this.md5.digest(element.getBytes());
        for (byte b : bytes) {
            builder.append(String.format("%02X", b));
        }
        return builder.toString();
    }
}

