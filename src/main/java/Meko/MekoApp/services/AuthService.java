package Meko.MekoApp.services;

import Meko.MekoApp.dto.AuthResponse;
import Meko.MekoApp.dto.LoginRequest;
import Meko.MekoApp.dto.RegisterRequest;
import Meko.MekoApp.entities.User;
import Meko.MekoApp.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AuthService {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    public AuthService(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder) {

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public String register(RegisterRequest req) {
        if(userRepository.existsByUsername(req.getUsername())) {
            throw new RuntimeException("Username is existing");
        }
        User user = new User();
        user.setUsername(
                req.getUsername());

        user.setPassword(
                passwordEncoder.encode(
                        req.getPassword()));

        user.setEmail(
                req.getEmail());

        user.setPhone(
                req.getPhone());

        user.setRole("CUSTOMER");

        user.setCreatedAt(
                LocalDateTime.now());

        userRepository.save(user);
        return "Register Success";
    }

    public AuthResponse login(
            LoginRequest req) {

        User user =
                userRepository.findByUsername(
                                req.getUsername())
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "User not found"));

        boolean matched =
                passwordEncoder.matches(
                        req.getPassword(),
                        user.getPassword());

        if(!matched){

            throw new RuntimeException(
                    "Wrong Password");
        }

        return new AuthResponse(
                "Login Success",
                user.getUsername(),
                user.getRole());
    }
}
