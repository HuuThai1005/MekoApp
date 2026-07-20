package Meko.Meko.controller;

import Meko.Meko.services.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/product")
public class CustomerProductController {
    private final ProductService productService;

    public CustomerProductController(ProductService productService) {
        this.productService = productService;
    }

    // Trang danh sách sản phẩm hiển thị cho khách mua hàng
    @GetMapping
    public String showProductStore(Model model,
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) Integer categoryId) {

        if (keyword != null && !keyword.isEmpty()) {
            if (categoryId != null) {
                model.addAttribute("products", productService.searchByCategoryAndName(categoryId, keyword));
            } else {
                model.addAttribute("products", productService.searchByName(keyword));
            }
        } else {
            if (categoryId != null) {
                model.addAttribute("products", productService.findAll().stream()
                        .filter(p -> p.getCategory() != null && p.getCategory().getId().equals(categoryId))
                        .toList());
            } else {
                model.addAttribute("products", productService.findAll());
            }
        }
        return "homepage/product";
    }
}
