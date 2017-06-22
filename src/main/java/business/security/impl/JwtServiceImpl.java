package business.security.impl;


import business.exceptions.InvalidTokenException;
import business.security.JwtService;
import business.security.UserAuthToken;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.InvalidParameterException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtServiceImpl implements JwtService{

    private final String password;
    private final long moduleTokenExpTime;
    private final long userTokenExpTime;
    private final String issuerName;

    @Autowired
    public JwtServiceImpl(
            @Value("${crud-api.business.jwt.password}") String password,
            @Value("${crud-api.business.jwt.module-token-exp-time}") long moduleTokenExpTime,
            @Value("${crud-api.business.jwt.user-token-exp-time}") long userTokenExpTime,
            @Value("${crud-api.business.jwt.issuer}") String issuerName) {
        this.password = password;
        this.moduleTokenExpTime = moduleTokenExpTime;
        this.userTokenExpTime = userTokenExpTime;
        this.issuerName = issuerName;
    }


    @Override
    public String createModuleToken() {
        String token = Jwts.builder()
                .setIssuer(issuerName)
                .setExpiration(new Date(System.currentTimeMillis() + moduleTokenExpTime))
                .signWith(SignatureAlgorithm.HS512, password)
                .compact();
        return token;
    }

    @Override
    public String createUserToken(long id) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("tokenType", "userAuthToken");
        claims.put("userId", id + "");
        String token = Jwts.builder()
                .setClaims(claims)
                .setIssuer(issuerName)
                .setExpiration(new Date(System.currentTimeMillis() + userTokenExpTime))
                .signWith(SignatureAlgorithm.HS512, password)
                .compact();
        return token;

    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T parse(String token, Class<T> clazz) {
        Claims claims = Jwts.parser()
                .setSigningKey(password)
                .parseClaimsJws(token)
                .getBody();
        if (clazz == UserAuthToken.class) {
            return (T) (new UserAuthToken(token, parseIssuer(claims), parseUserId(claims)));
        } else {
            throw new InvalidParameterException("Class " + clazz.getName() + " is not valid token class");
        }

    }

    private String parseIssuer(Claims claims) {
        String issuer = claims.getIssuer();
        if (issuer == null) {
            throw new InvalidTokenException("Issuer not found");
        }
        return issuer;
    }

    private String parseIssuer(Claims claims, String expectedIssuer) {
        String issuer = parseIssuer(claims);
        if (!issuer.equals(expectedIssuer)) {
            throw new InvalidTokenException("Invalid issuer name: " + issuer);
        }
        return issuer;
    }

    private Long parseUserId(Claims claims) {
        Object userId = claims.get("userId");
        if (userId == null) {
            return null;
        }
        try {
            return Long.valueOf((String) userId);
        } catch (Exception e) {
            throw new InvalidTokenException(e);
        }
    }

}
