package com.spring.company.filter;

import com.sun.istack.Nullable;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.HashMap;
import java.util.Map;

public class CustomAuthenticationToken extends UsernamePasswordAuthenticationToken {

    private final Map<String, String> claims = new HashMap<>();

    public CustomAuthenticationToken(Object principal, Map<String, String> claims,
                                     @Nullable java.util.Collection<? extends GrantedAuthority> authorities) {
        super(principal, null, authorities);

        this.claims.putAll(claims);
    }

    public Map<String, String> getClaims() {
        return claims;
    }
}
