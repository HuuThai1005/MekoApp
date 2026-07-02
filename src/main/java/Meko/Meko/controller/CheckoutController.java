package Meko.Meko.controller;

import Meko.Meko.dto.CheckoutRequest;
import Meko.Meko.entities.Cart;
import Meko.Meko.entities.CartItems;
import Meko.Meko.entities.Orders;
import Meko.Meko.entities.User;
import Meko.Meko.repositories.OrderRepository;
import Meko.Meko.services.CartService;
import Meko.Meko.services.OrderService;
import Meko.Meko.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@Controller
@RequestMapping("/checkout")
public class CheckoutController {

    private OrderService orderService;
    private UserService userService;
    private CartService cartService;
    private OrderRepository orderRepository;

    public CheckoutController(
            OrderService orderService,
            UserService userService,
    CartService cartService,
            OrderRepository orderRepository) {

        this.orderService = orderService;
        this.userService = userService;
        this.cartService = cartService;
        this.orderRepository = orderRepository;
    }

    @GetMapping
    public String checkoutPage(
            Model model,
            Authentication authentication){

        String username =
                authentication.getName();

        User user =
                userService.findUserByUsername(username);

        Cart cart =
                cartService.findByUser(user);

        BigDecimal subtotal = BigDecimal.ZERO;

        if(cart != null){

            for(CartItems item : cart.getCartItems()){

                subtotal = subtotal.add(
                        item.getProduct()
                                .getPrice()
                                .multiply(
                                        BigDecimal.valueOf(
                                                item.getQuantity()
                                        )
                                )
                );
            }

            model.addAttribute(
                    "cartItems",
                    cart.getCartItems());
        }

        model.addAttribute("subtotal", subtotal);

        model.addAttribute(
                "checkoutRequest",
                new CheckoutRequest());

        return "homepage/checkout";
    }

    @PostMapping
    public String checkout(
            @ModelAttribute CheckoutRequest request,
            Authentication authentication) {

        User user =
                userService.findUserByUsername(
                        authentication.getName());

        Integer orderId =
                orderService.checkout(user, request);

        if(request.getPaymentMethod()
                .equals("BANKING")) {

            return "redirect:/checkout/payment/qr/" + orderId;
        }

        return "redirect:/order-success";
    }

    @GetMapping("/payment/qr/{id}")
    public String qrPayment(
            @PathVariable Integer id,
            Model model) {

        Orders order =
                orderRepository.findById(id)
                        .orElseThrow();

        String qrUrl =
                "https://img.vietqr.io/image/MB-123456789-compact2.png"
                        + "?amount=" + order.getTotal()
                        + "&addInfo=ORDER_" + order.getId();

        model.addAttribute("order", order);
        model.addAttribute("qrUrl", qrUrl);

        return "homepage/qr-payment";
    }

    @PostMapping("/payment/success/{id}")
    @ResponseBody
    public String paymentSuccess(
            @PathVariable Integer id) {

        Orders order =
                orderRepository.findById(id)
                        .orElseThrow();

        order.setPaymentStatus("PAID");

        order.setStatus("CONFIRMED");

        orderRepository.save(order);

        return "OK";
    }
}