package Meko.MekoApp.controller;

import Meko.MekoApp.entities.Category;
import Meko.MekoApp.services.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/category")
public class CategoryController {
    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public String findAdll(Model model) {
        model.addAttribute("cates", categoryService.findAll());
        return "homepage/category";
    }
}
