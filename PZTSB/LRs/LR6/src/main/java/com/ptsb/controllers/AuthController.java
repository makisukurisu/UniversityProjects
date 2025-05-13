package com.ptsb.controllers;

import com.ptsb.dtos.AuthenticateRequest;
import com.ptsb.dtos.AuthenticateResponse;
import com.ptsb.dtos.TokenInfo;
import com.ptsb.interfaces.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthService authService;

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticateResponse> authenticate(@RequestBody AuthenticateRequest authenticateRequest)
    {
        TokenInfo tokenInfo = authService.login(authenticateRequest);

        AuthenticateResponse authenticateResponse = new AuthenticateResponse();

        authenticateResponse.setExpiresAt(tokenInfo.getExpiresAt());
        authenticateResponse.setAccessToken(tokenInfo.getToken());
        authenticateResponse.setTokenType(tokenInfo.getType());

        return  new ResponseEntity<>(authenticateResponse, HttpStatus.OK);
    }

    @PostMapping(value="/authenticate", headers={"content-type=application/x-www-form-urlencoded"})
    public ResponseEntity<AuthenticateResponse> authenticateWithForm(@RequestParam("username") String username, @RequestParam("password") String password)
    {
        return authenticate(
                new AuthenticateRequest(username, password)
        );
    }
}
