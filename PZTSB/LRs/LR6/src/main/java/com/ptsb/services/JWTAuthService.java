package com.ptsb.services;

import com.ptsb.config.JWTokenProvider;
import com.ptsb.dtos.AuthenticateRequest;
import com.ptsb.dtos.TokenInfo;
import com.ptsb.interfaces.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class JWTAuthService implements AuthService {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JWTokenProvider jwtTokenProvider;


    @Override
    public TokenInfo login(String username, String password) {
        return login(new AuthenticateRequest(username, password));
    }

    @Override
    public TokenInfo login(AuthenticateRequest authenticateRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticateRequest.getUsername(),
                        authenticateRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        TokenInfo tokenInfo = jwtTokenProvider.getToken(authentication);

        tokenInfo.setType("Bearer");

        return tokenInfo;
    }

    @Override
    public TokenInfo refresh(String refreshToken) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void logout(String token) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
