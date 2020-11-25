package com.helpers;

import org.jetbrains.annotations.NotNull;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;

public class PasswordHelper {

    private PasswordHelper() {
    }

    public static String hashPassword(String pass) throws NoSuchAlgorithmException {
        String hashedPass=null;
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(pass.getBytes(StandardCharsets.UTF_8));
        byte[] base64hash= Base64.getEncoder().encode(hash);
        hashedPass=new String(base64hash);
        return hashedPass;
    }

    public static boolean comparePassAndHashedPassword(@NotNull String pass, @NotNull String hashedPassword) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(pass.getBytes(StandardCharsets.UTF_8));
        byte[] base64hash=Base64.getEncoder().encode(hash);
        if(Arrays.equals(base64hash,hashedPassword.getBytes())){
            return true;
        }else{
            return false;
        }
    }


}
