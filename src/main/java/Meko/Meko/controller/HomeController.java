package Meko.Meko.controller;

import Meko.Meko.entities.User;
import Meko.Meko.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {
    private UserService userService;

    public HomeController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String home() {
        return "homepage/index";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "auth/login";
    }

    @GetMapping("/register")
    public String registerPage() {
        return "auth/register";
    }
    @GetMapping("/profile")
    public String profilePage(Authentication authentication, Model model){
        User user = userService.findUserByUsername(authentication.getName());
        model.addAttribute("user", user);
        return "homepage/profile";
    }
}
