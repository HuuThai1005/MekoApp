package Meko.Meko.controller;

import Meko.Meko.services.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/product")
public class CustomerProductController {
    private final ProductService productService;

    public CustomerProductController(ProductService productService) {
        this.productService = productService;
    }

    // Trang danh sách sản phẩm hiển thị cho khách mua hàng
    @GetMapping
    public String showProductStore(Model model) {

        model.addAttribute("products", productService.findAll());
        return "homepage/product";
    }
}
