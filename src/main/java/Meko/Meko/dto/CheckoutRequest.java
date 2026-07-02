package Meko.Meko.dto;

import lombok.Data;

@Data
public class CheckoutRequest {

    private String receiverName;

    private String phone;

    private String shippingAddress;

    private String paymentMethod;

    private String voucherCode;
}
