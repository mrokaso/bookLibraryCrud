package business.impl;


import business.LoginService;
import business.security.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

    private final JwtService jwtService;

    @Autowired
    public LoginServiceImpl(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    public String createAuthToken(long userId) {
        return jwtService.createModuleToken();
    }

}
