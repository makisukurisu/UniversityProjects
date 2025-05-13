package com.ptsb.config;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import com.ptsb.dtos.TokenInfo;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;


import java.text.ParseException;
import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.UUID;

@Component
public class JWTokenProvider {
    private final String secret;
    private final String issuer;

    private final Duration accessTokenExpiryPeriod;

    public JWTokenProvider() {
        secret = System.getenv().get("JWT_SECRET");
        issuer = System.getenv().get("JWT_ISSUER");
        String durationAsString = System.getenv().getOrDefault("JWT_ACCESS_TOKEN_EXPIRY_SECONDS", "0");
        if(durationAsString.equals("0")) {
            accessTokenExpiryPeriod = Duration.ofSeconds(60 * 15);
        }
        else{
            accessTokenExpiryPeriod = Duration.ofSeconds(Integer.parseInt(durationAsString));
        }
    }

    public TokenInfo getToken(Authentication authentication) {
        String username = authentication.getName();

        Instant instant = Instant.now(Clock.systemUTC());
        Instant expiresAt = instant.plus(accessTokenExpiryPeriod);

        JWSSigner signer;

        try {
            signer = new MACSigner(secret);
        } catch (KeyLengthException e) {
            throw new RuntimeException(e);
        }

        JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
                .subject(username)
                .jwtID(UUID.randomUUID().toString())
                .issuer(issuer)
                .issueTime(Date.from(instant))
                .expirationTime(Date.from(expiresAt))
                .claim("roles", authentication.getAuthorities().stream().map(
                        GrantedAuthority::getAuthority
                ).toArray(String[]::new))
                .build();

        SignedJWT signedJWT = new SignedJWT(new JWSHeader(JWSAlgorithm.HS256), claimsSet);

        try {
            signedJWT.sign(signer);
        } catch (JOSEException e) {
            throw new RuntimeException(e);
        }

        TokenInfo tokenInfo = new TokenInfo();

        tokenInfo.setToken(signedJWT.serialize());
        tokenInfo.setExpiresAt(Date.from(expiresAt));

        return tokenInfo;
    }

    public SignedJWT getSignedJWT(String token) {
        try {
            return SignedJWT.parse(token);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public SignedJWT getSignedJWTWithVerification(String token) {
        SignedJWT signedJWT = getSignedJWT(token);
        JWSVerifier verifier;

        try {
            verifier = new MACVerifier(secret);
        } catch (JOSEException e) {
            throw new RuntimeException(e);
        }

        try {
            if(!signedJWT.verify(verifier)) {
                throw new BadCredentialsException("Could not verify signature");
            }
        } catch (JOSEException e) {
            throw new BadCredentialsException("Could not verify signature");
        }

        try {
            var claims = signedJWT.getJWTClaimsSet();

            if(claims.getExpirationTime().before(new Date())){
                throw new BadCredentialsException("Expired token");
            }

            if(!claims.getIssuer().equals(issuer)) {
                throw new BadCredentialsException("Invalid issuer");
            }

        } catch (ParseException e) {
            throw new BadCredentialsException("Could not get claims");
        }

        return signedJWT;
    }

    public String getSubject(String token){
        SignedJWT signedJWT = getSignedJWTWithVerification(token);
        JWTClaimsSet claims;

        try {
            claims = signedJWT.getJWTClaimsSet();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        return claims.getSubject();
    }

    public boolean isValid(String token){
        SignedJWT signedJWT = getSignedJWTWithVerification(token);
        try{
            signedJWT.getJWTClaimsSet();
            return true;
        }
        catch (ParseException e){
            return false;
        }
    }

}
