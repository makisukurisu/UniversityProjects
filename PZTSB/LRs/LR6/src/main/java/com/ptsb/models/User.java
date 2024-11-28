package com.ptsb.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

import java.security.NoSuchAlgorithmException;
import java.time.ZonedDateTime;
import java.util.Base64;
import java.util.UUID;

@Getter
@Data
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue
    private UUID id;

    @Getter
    @Setter
    private String firstName;

    @Getter
    @Setter
    private String lastName;

    @Getter
    @Setter
    private String username;

    @Getter
    @Setter
    private ZonedDateTime createdAt;

    private String hashedPassword;

    private MessageDigest hasher() {
        try {
            return MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    boolean validatePassword(
        String password
    ) {
        MessageDigest md = this.hasher();

        var decoded = Base64.getEncoder().encodeToString(md.digest(password.getBytes(StandardCharsets.UTF_8)));
        return this.hashedPassword.equals(decoded);
    }

    public void setHashedPassword(String password) {
        // Still very bad. No salt, no nothing.
        MessageDigest md = this.hasher();

        this.hashedPassword = Base64.getEncoder().encodeToString(md.digest(password.getBytes(StandardCharsets.UTF_8)));
    }
}
