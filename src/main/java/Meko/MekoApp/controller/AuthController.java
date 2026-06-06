package Meko.MekoApp.controller;

import Meko.MekoApp.dto.RegisterRequest;
import Meko.MekoApp.services.AuthService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(
            AuthService authService) {

        this.authService = authService;
    }

    @PostMapping("/register")
    public String register(
            RegisterRequest request) {

        authService.register(request);

        return "redirect:/login";
    }
}