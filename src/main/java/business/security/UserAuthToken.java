package business.security;


public class UserAuthToken {

    private String rawToken;

    private String issuer;

    private Long userId;

    public UserAuthToken(
            String rawToken,
            String issuer,
            Long userId) {
        this.rawToken = rawToken;
        this.issuer = issuer;
        this.userId = userId;
    }

    public String getRawToken() {
        return rawToken;
    }

    public void setRawToken(String rawToken) {
        this.rawToken = rawToken;
    }

    public String getIssuer() {
        return issuer;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

}
