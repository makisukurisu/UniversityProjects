package com.ptsb.dtos;

import lombok.*;
import org.springframework.stereotype.Component;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Data
@Component
public class AuthenticateRequest {
    private String username;
    private String password;
    private String grant_type = "password";

    public AuthenticateRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
