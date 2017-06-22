package web.security;


import business.security.JwtService;
import business.security.UserAuthToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class AuthProvider implements AuthenticationProvider {

    private final JwtService jwtService;

    @Autowired
    public AuthProvider(
            JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        try {
            UserAuthToken token = jwtService.parse((String) authentication.getCredentials(), UserAuthToken.class);
            return new JwtAuthentication(token);
        } catch (Exception e) {
            throw new AuthenticationCredentialsNotFoundException("Unauthorized", e);
        }
    }


    @Override
    public boolean supports(Class<?> authentication) {
        return (JwtAuthentication.class.isAssignableFrom(authentication));
    }
}
