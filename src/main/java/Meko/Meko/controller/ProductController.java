package Meko.Meko.controller;

import Meko.Meko.entities.Product;
import Meko.Meko.entities.Voucher;
import Meko.Meko.services.ProductService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping("/create-product")
    public String create (Model model){
        model.addAttribute("product",new Product());
        return "dashboard/product_create";
    }
    @PostMapping("/create")
    public String store (
            @ModelAttribute Product product
    )
    {
        productService.save(product);
        return "redirect:/product";
    }
    @GetMapping("/delete/{id}")
    public String delete(
            @PathVariable Integer id
    )
    {
        productService.delete(id);
        return "redirect:/product";
    }
    @GetMapping("/edit/{id}")
    public String edit(
            @PathVariable Integer id,
            Model model) {

        model.addAttribute(
                "product",
                productService.findById(id));

        return "dashboard/product_update";
    }
    @PostMapping("/update/{id}")
    public String update(
            @PathVariable Integer id,
            @ModelAttribute Product formProduct) {

        Product product =
                productService.findById(id);
        System.out.println("Category = " + formProduct.getCategory());
        product.setProductName(formProduct.getProductName());

        product.setDescription(
                formProduct.getDescription());

        product.setPrice(formProduct.getPrice());

        product.setStock(
                formProduct.getStock());

        product.setImageUrl(
                formProduct.getImageUrl());

        product.setStatus(
                formProduct.getStatus());

        product.setCreatedAt(
                formProduct.getCreatedAt());

        product.setCategory(
                formProduct.getCategory()
        );

        productService.save(product);

        return "redirect:/product";
    }

}
