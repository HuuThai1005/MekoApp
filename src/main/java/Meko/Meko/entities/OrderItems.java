package Meko.Meko.entities;
import jakarta.persistence.*;
import jakarta.persistence.criteria.Order;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Entity
@Table(name = "OrderItems")
public class OrderItems {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "OrderId")
    private Orders order;

    @ManyToOne
    @JoinColumn(name = "ProductId")
    private Product product;

    @Column(name = "ProductName")
    private String productName;

    @Column(name = "Quantity")
    private Integer quantity;

    @Column(name = "Price")
    private BigDecimal price;

    @Column(name = "Subtotal")
    private BigDecimal subtotal;
}
