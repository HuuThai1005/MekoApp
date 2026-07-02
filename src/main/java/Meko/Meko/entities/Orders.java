package Meko.Meko.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table (name = "Orders")
@Data
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "UserId")
    private User user;

    @Column(name = "ReceiverName")
    private String receiverName;

    @Column(name = "Phone")
    private String phone;

    @Column(name = "ShippingAddress")
    private String shippingAddress;

    @Column(name = "Subtotal")
    private BigDecimal subtotal;

    @Column(name = "ShippingFee")
    private BigDecimal shippingFee;

    @Column(name = "Discount")
    private BigDecimal discount;

    @Column(name = "Total")
    private BigDecimal total;

    @Column(name = "Status")
    private String status;

    @Column(name = "PaymentStatus")
    private String paymentStatus;

    @Column(name = "CreatedAt")
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "order",
            cascade = CascadeType.ALL)
    private List<OrderItems> orderItems;

    @Column(name = "PaymentMethod")
    private String paymentMethod;
}
