package com.ptsb.interfaces;

import com.ptsb.dtos.AuthenticateRequest;
import com.ptsb.dtos.TokenInfo;

public interface AuthService {
    TokenInfo login(String username, String password);
    TokenInfo login(AuthenticateRequest authenticateRequest);

    TokenInfo refresh(String refreshToken);

    void logout(String token);
}
