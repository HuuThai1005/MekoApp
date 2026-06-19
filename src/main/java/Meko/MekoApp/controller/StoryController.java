package Meko.MekoApp.controller;

import Meko.MekoApp.services.StoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
//@RequestMapping("/story")
public class StoryController {
    private StoryService storyService;

    public StoryController(StoryService storyService) {
        this.storyService = storyService;
    }
//    @GetMapping
//    public String findAll(Model model){
//        model.addAttribute("stories", storyService.findAll());
//        return
//    }
}
