package Meko.Meko.controller;

import Meko.Meko.entities.User;
import Meko.Meko.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping ("/profile")
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping("/update")
    public String profile(Authentication authentication,
                          Model model) {

        User user = userService.findUserByUsername(authentication.getName());

        model.addAttribute("user", user);

        return "homepage/update_profile";
    }
    @PostMapping("/edit")
    public String updateProfile (Authentication authentication, @ModelAttribute User formUser) {
        User user = userService.findUserByUsername(authentication.getName());
        user.setUsername(formUser.getUsername());
        user.setEmail(formUser.getEmail());
        user.setPhone(formUser.getPhone());
        userService.save(user);
       return "redirect:/profile";
    }
}
