package com.example.android_pizza_shop;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
public class PasswordHasher {
    // Hashing algorithm for passwords
    public static final String HASH_ALGORITHM = "SHA-256";

    // Hashes the given password using SHA-256
    public static String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance(HASH_ALGORITHM);
            byte[] hashedBytes = md.digest(password.getBytes());

            // Convert bytes to hexadecimal format
            StringBuilder sb = new StringBuilder();
            for (byte b : hashedBytes) {
                sb.append(String.format("%02x", b));
            }

            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            // Handle the exception according to your needs
            return null;
        }
    }
}
