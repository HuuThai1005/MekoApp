package Meko.MekoApp.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "Vouchers")
public class Voucher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Integer id;

    @Column(name = "VoucherCode", nullable = false, unique = true)
    private String voucherCode;

    @Column(name = "VoucherName")
    private String voucherName;

    @Column(name = "DiscountType")
    private String discountType;

    @Column(name = "Value")
    private BigDecimal value;

    @Column(name = "Amount")
    private Integer amount;

    @Column(name = "StartDate")
    private LocalDateTime startDate;

    @Column(name = "EndDate")
    private LocalDateTime endDate;

    @Column(name = "Status")
    private String status;

    @Column(name = "CreatedAt")
    private LocalDateTime createdAt;
}
