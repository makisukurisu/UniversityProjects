package com.ptsb.dtos;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.Date;

@Setter
@Getter
@Data
@Component
public class AuthenticateResponse {
    String accessToken;
    String tokenType;
    Date expiresAt;
}
