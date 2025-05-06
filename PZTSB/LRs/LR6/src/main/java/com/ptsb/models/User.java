package com.ptsb.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

import java.security.NoSuchAlgorithmException;
import java.time.ZonedDateTime;
import java.util.Base64;
import java.util.Set;
import java.util.UUID;

@Getter
@Data
@Entity
@Table(name = "user", schema = "public", uniqueConstraints = @UniqueConstraint(columnNames={"username"}))
public class User {
    @Id
    @GeneratedValue
    private UUID id;

    @Setter
    private String firstName;

    @Setter
    private String lastName;

    @Setter
    private String username;

    @Setter
    private ZonedDateTime createdAt;

    @JsonIgnore
    private String hashedPassword;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(name="user_roles")
    @Setter
    private Set<Role> roles;

    public User() {
    }

    private MessageDigest hasher() {
        try {
            return MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public void setHashedPassword(String password) {
        // Still very bad. No salt, no nothing.
        MessageDigest md = this.hasher();

        this.hashedPassword = Base64.getEncoder().encodeToString(md.digest(password.getBytes(StandardCharsets.UTF_8)));
    }

    public User(String firstName, String lastName, String username, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.setHashedPassword(password);
        this.setCreatedAt(ZonedDateTime.now());
    }

    public String toString(){
        return "User(%s, %s)".formatted(id, username);
    }
}
