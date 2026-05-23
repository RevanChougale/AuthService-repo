package com.AuthService.AuthService.Service;

import com.AuthService.AuthService.Config.JwtUtility;
import com.AuthService.AuthService.Dto.Login;
import com.AuthService.AuthService.Dto.SignUp;
import com.AuthService.AuthService.Entity.User;
import com.AuthService.AuthService.Repo.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    // 🔹 Dependency Injection
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;

    public AuthService(UserRepository userRepository,
                       BCryptPasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    // 🟢 SIGNUP
    public String signup(SignUp request) {

        User user = new User();
        user.setUsername(request.username);

        // 🔐 encrypt password before saving
        user.setPassword(encoder.encode(request.password));

        user.setRole(request.role.toUpperCase());

        userRepository.save(user);

        return "User registered successfully";
    }

    // 🔐 LOGIN + JWT
    public String login(Login request) {

        User user = userRepository.findByUsername(request.username);

        if (user == null) {
            return "User not found";
        }

        // 🔐 compare password
        boolean match = encoder.matches(
                request.password,
                user.getPassword()
        );

        if (!match) {
            return "Invalid password";
        }

        // 🔥 generate JWT
        return JwtUtility.generateToken(user.getUsername(), user.getRole());
    }
}
