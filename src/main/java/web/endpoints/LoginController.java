package web.endpoints;

import business.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    @GetMapping("/login/{id}")
    public String getTokenByUser(@PathVariable long id){
        return loginService.createAuthToken(id);
    }
}
