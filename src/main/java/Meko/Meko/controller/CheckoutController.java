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
import Meko.Meko.entities.Voucher;
import Meko.Meko.repositories.VoucherRepository;
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
    private VoucherRepository voucherRepository;

    public CheckoutController(
            OrderService orderService,
            UserService userService,
            CartService cartService,
            OrderRepository orderRepository,
            VoucherRepository voucherRepository) {

        this.orderService = orderService;
        this.userService = userService;
        this.cartService = cartService;
        this.orderRepository = orderRepository;
        this.voucherRepository = voucherRepository;
    }

    @GetMapping
    public String checkoutPage(
            Model model,
            Authentication authentication,
            @RequestParam(value = "voucherCode", required = false) String voucherCode){

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

        // shipping fee cố định 30.000 như OrderService
        BigDecimal shippingFee = BigDecimal.valueOf(30000);

        // tính discount để UI hiển thị tổng chi phí có bao gồm voucher
        BigDecimal discount = BigDecimal.ZERO;
        if(voucherCode != null && !voucherCode.isEmpty()){
            Voucher voucher = voucherRepository
                    .findByVoucherCode(voucherCode)
                    .orElse(null);

            if(voucher != null
                    && voucher.getStatus() != null
                    && voucher.getStatus().equals("ACTIVE")
                    && voucher.getAmount() != null
                    && voucher.getAmount() > 0
                    && voucher.getStartDate() != null
                    && !voucher.getStartDate().isAfter(java.time.LocalDateTime.now())
                    && voucher.getEndDate() != null
                    && !voucher.getEndDate().isBefore(java.time.LocalDateTime.now())){

                if(voucher.getDiscountType() != null && voucher.getDiscountType().equals("PERCENT")){
                    discount = subtotal.multiply(
                            voucher.getValue()
                                    .divide(
                                            BigDecimal.valueOf(100),
                                            6,
                                            java.math.RoundingMode.HALF_UP
                                    )
                    );
                } else {
                    discount = voucher.getValue();
                }
            }
        }

        BigDecimal total = subtotal.add(shippingFee).subtract(discount);
        if(total.compareTo(BigDecimal.ZERO) < 0){
            total = BigDecimal.ZERO;
        }

        model.addAttribute("voucherCode", voucherCode);
        model.addAttribute("discount", discount);
        model.addAttribute("shippingFee", shippingFee);
        model.addAttribute("total", total);

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