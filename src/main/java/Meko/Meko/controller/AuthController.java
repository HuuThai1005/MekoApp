package Meko.Meko.controller;

import Meko.Meko.dto.RegisterRequest;
import Meko.Meko.services.AuthService;
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
    public String register(RegisterRequest request) {

        System.out.println("REGISTER CALLED");

        authService.register(request);

        return "redirect:/login";
    }
}