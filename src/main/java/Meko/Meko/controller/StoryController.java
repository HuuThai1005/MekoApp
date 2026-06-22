package Meko.Meko.controller;

import Meko.Meko.services.StoryService;
import org.springframework.stereotype.Controller;

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
