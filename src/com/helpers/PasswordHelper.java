package com.helpers;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;

public class PasswordHelper {

    private PasswordHelper() {
    }

    public static String hashPassword(String pass) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(pass.getBytes(StandardCharsets.UTF_8));
        byte[] base64hash= Base64.getEncoder().encode(hash);
        return new String(base64hash);
    }

    public static boolean comparePassAndHashedPassword(String pass, String hashedPassword) throws NoSuchAlgorithmException {
        String hash = hashPassword(pass);
        return hash.equals(hashedPassword);
    }
}
