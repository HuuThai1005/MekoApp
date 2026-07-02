package Meko.Meko.controller;

import Meko.Meko.entities.Cart;
import Meko.Meko.entities.Product;
import Meko.Meko.entities.User;
import Meko.Meko.services.CartService;
import Meko.Meko.services.ProductService;
import Meko.Meko.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/cart")
public class CartController {
    private CartService cartService;
    private ProductService productService;
    private UserService userService;

    public CartController(
            CartService cartService,
            ProductService productService,
            UserService userService) {

        this.cartService = cartService;
        this.productService = productService;
        this.userService = userService;
    }

    @GetMapping("/add/{id}")
    public String addCart(
            @PathVariable Integer id,
            Authentication authentication){

        String username =
                authentication.getName();

        User user =
                userService.findUserByUsername(username);

        Product product =
                productService.findById(id);


        cartService.addProduct(user,product);

        return "redirect:/cart";
    }

    @GetMapping
    public String cartPage(
            Authentication authentication,
            Model model){

        String username =
                authentication.getName();

        User user =
                userService.findUserByUsername(username);

        Cart cart =
                cartService.findByUser(user);

        if(cart != null){

            model.addAttribute(
                    "cartItems",
                    cart.getCartItems());
        }

        return "homepage/cart";
    }
    @PostMapping("/update")
    public String updateCart(
            @RequestParam Integer productId,
            @RequestParam String action,
            Authentication authentication){

        String username =
                authentication.getName();

        User user =
                userService.findUserByUsername(username);

        cartService.updateQuantity(
                user,
                productId,
                action);

        return "redirect:/cart";
    }

    @PostMapping("/delete/{id}")
    public String deleteItem(
            @PathVariable Integer id,
            Authentication authentication){

        String username =
                authentication.getName();

        User user =
                userService.findUserByUsername(username);

        cartService.deleteItem(
                user,
                id);

        return "redirect:/cart";
    }

}

