package Meko.MekoApp.controller;

import Meko.MekoApp.services.ProductService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/product")
public class ProductController
{
    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    @GetMapping
    public String findAll(Model model) {
        model.addAttribute("products", productService.findAll());
        return "homepage/product";
    }
}
