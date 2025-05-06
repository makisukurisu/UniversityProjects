package com.ptsb.config;

import org.springframework.security.crypto.password.PasswordEncoder;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class CustomPasswordEncoder implements PasswordEncoder {

    private MessageDigest hasher() {
        try {
            return MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String encode(CharSequence rawPassword) {
        MessageDigest md = this.hasher();

        return Base64.getEncoder().encodeToString(md.digest(rawPassword.toString().getBytes(StandardCharsets.UTF_8)));
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        MessageDigest md = this.hasher();

        var encoded = Base64.getEncoder().encodeToString(md.digest(rawPassword.toString().getBytes(StandardCharsets.UTF_8)));
        return encodedPassword.equals(encoded);
    }
}
