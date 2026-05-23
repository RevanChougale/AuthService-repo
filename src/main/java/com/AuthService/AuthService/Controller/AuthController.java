package com.AuthService.AuthService.Controller;

import com.AuthService.AuthService.Dto.Login;
import com.AuthService.AuthService.Dto.SignUp;
import com.AuthService.AuthService.Service.AuthService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    // 🟢 SIGNUP API
    @PostMapping("/signup")
    public String signup(@RequestBody SignUp request) {
        return authService.signup(request);
    }

    // 🔐 LOGIN API
    @PostMapping("/login")
    public String login(@RequestBody Login request) {
        return authService.login(request);
    }

}
