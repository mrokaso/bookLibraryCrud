package web.security;


import business.security.UserAuthToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class JwtAuthentication implements Authentication {

    private boolean authenticated;

    private String jwtToken;
    private UserAuthToken principal;

    public JwtAuthentication(
            String rawAccessToken) {
        this.jwtToken = rawAccessToken;
        this.authenticated = false;
    }

    public JwtAuthentication(
            UserAuthToken principal) {
        this.principal = principal;
        this.authenticated = true;
    }

    @Override
    public Object getCredentials() {
        return jwtToken;
    }

    @Override
    public Object getPrincipal() {
        return principal;
    }

    @Override
    public boolean isAuthenticated() {
        return authenticated;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public Object getDetails() {
        return null;
    }

    @Override
    public void setAuthenticated(boolean authenticated) {}

}
