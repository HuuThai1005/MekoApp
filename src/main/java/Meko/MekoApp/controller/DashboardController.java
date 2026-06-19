package Meko.MekoApp.controller;

import Meko.MekoApp.services.CategoryService;
import Meko.MekoApp.services.ProductService;
import Meko.MekoApp.services.StoryService;
import Meko.MekoApp.services.VoucherService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {
    private ProductService productService;
    private VoucherService voucherService;
    private CategoryService categoryService;
    private StoryService storyService;

    public DashboardController(ProductService productService, VoucherService voucherService, CategoryService categoryService, StoryService storyService) {
        this.productService = productService;
        this.voucherService = voucherService;
        this.categoryService = categoryService;
        this.storyService = storyService;
    }

    @GetMapping
    public String dashBoard(Authentication authentication, Model model){
        model.addAttribute("username", authentication.getName());
        model.addAttribute("products", productService.findAll().size());
        model.addAttribute("vouchers", voucherService.findAll().size());
        model.addAttribute("categories", categoryService.findAll().size());
        model.addAttribute("stories", storyService.findAll().size());
        return "dashboard/index";
    }
}
