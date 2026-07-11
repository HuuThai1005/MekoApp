package Meko.Meko.services;

import Meko.Meko.dto.CheckoutRequest;
import Meko.Meko.entities.*;
import Meko.Meko.repositories.OrderItemRepository;
import Meko.Meko.repositories.OrderRepository;
import Meko.Meko.repositories.VoucherRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    private OrderRepository orderRepository;
    private OrderItemRepository orderItemRepository;
    private CartService cartService;
    private VoucherRepository voucherRepository;

    public OrderService(
            OrderRepository orderRepository,
            OrderItemRepository orderItemRepository,
            CartService cartService,
            VoucherRepository voucherRepository) {

        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
        this.cartService = cartService;
        this.voucherRepository = voucherRepository;
    }

    @Transactional
    public int checkout(User user,
                         CheckoutRequest request) {

        Cart cart = cartService.findByUser(user);

        List<CartItems> cartItems = cart.getCartItems();

        if (cartItems == null || cartItems.isEmpty()) {
            throw new RuntimeException("Giỏ hàng trống");
        }

        BigDecimal subtotal = BigDecimal.ZERO;

        for (CartItems item : cartItems) {

            if (item.getUnitPrice() == null) {
                item.setUnitPrice(
                        item.getProduct().getPrice());
            }

            BigDecimal itemSubtotal =
                    item.getUnitPrice()
                            .multiply(
                                    BigDecimal.valueOf(
                                            item.getQuantity()));

            subtotal = subtotal.add(itemSubtotal);
        }

        BigDecimal shippingFee =
                BigDecimal.valueOf(30000);

        BigDecimal discount = BigDecimal.ZERO;

        if(request.getVoucherCode() != null
                && !request.getVoucherCode().isEmpty()) {

            Voucher voucher =
                    voucherRepository
                            .findByVoucherCode(
                                    request.getVoucherCode()
                            )
                            .orElse(null);

            if(voucher != null
                    && voucher.getStatus().equals("ACTIVE")
                    && voucher.getAmount() != null
                    && voucher.getAmount() > 0
                    && voucher.getStartDate() != null
                    && !voucher.getStartDate().isAfter(LocalDateTime.now())
                    && voucher.getEndDate() != null
                    && !voucher.getEndDate().isBefore(LocalDateTime.now())) {


                if(voucher.getDiscountType()
                        .equals("PERCENT")) {

                    // value = % (vd: 10 => giảm 10%)
                    discount =
                            subtotal.multiply(
                                    voucher.getValue()
                                            .divide(
                                                    BigDecimal.valueOf(100),
                                                    6,
                                                    java.math.RoundingMode.HALF_UP)
                            );

                } else {
                    // FIXED: value = số tiền giảm
                    discount = voucher.getValue();
                }

                // Tránh trừ discount âm hoặc null
                if(discount != null && discount.compareTo(BigDecimal.ZERO) > 0) {
                    voucher.setAmount(
                            voucher.getAmount() - 1
                    );
                    voucherRepository.save(voucher);
                } else {
                    discount = BigDecimal.ZERO;
                }

            }
        }

        BigDecimal discountSafe = discount != null ? discount : BigDecimal.ZERO;
        // Không cho tổng âm
        BigDecimal total =
                subtotal
                        .add(shippingFee)
                        .subtract(discountSafe);
        if(total.compareTo(BigDecimal.ZERO) < 0) {
            total = BigDecimal.ZERO;
        }


        Orders order = new Orders();

        order.setUser(user);
        order.setReceiverName(request.getReceiverName());
        order.setPhone(request.getPhone());
        order.setShippingAddress(request.getShippingAddress());

        order.setSubtotal(subtotal);
        order.setShippingFee(shippingFee);
        order.setDiscount(discount);

        order.setTotal(total);

        order.setStatus("PENDING");
        order.setPaymentStatus("UNPAID");
        order.setPaymentMethod(
                request.getPaymentMethod());

        order.setCreatedAt(LocalDateTime.now());

        Orders savedOrder =
                orderRepository.save(order);
        if(request.getPaymentMethod().equals("COD")){

            order.setPaymentStatus("UNPAID");

        }else if(request.getPaymentMethod().equals("BANKING")){

            order.setPaymentStatus("PENDING");
        }

        List<OrderItems> orderItems =
                new ArrayList<>();

        for (CartItems item : cartItems) {

            BigDecimal itemSubtotal =
                    item.getUnitPrice()
                            .multiply(
                                    BigDecimal.valueOf(
                                            item.getQuantity()));

            OrderItems orderItem =
                    new OrderItems();

            orderItem.setOrder(savedOrder);
            orderItem.setProduct(item.getProduct());

            orderItem.setProductName(
                    item.getProduct().getProductName());

            orderItem.setQuantity(item.getQuantity());

            orderItem.setPrice(item.getUnitPrice());

            orderItem.setSubtotal(itemSubtotal);

            orderItems.add(orderItem);
        }

        orderItemRepository.saveAll(orderItems);

        cartItems.clear();

        cartService.save(cart);
        return savedOrder.getId();
    }
}