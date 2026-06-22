package Meko.Meko.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@Entity
@Table(name = "Products")
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Integer id;

    @Column(name = "ProductName")
    private String productName;

    @Column(name = "Description")
    private String description;

    @Column(name = "Price")
    private BigDecimal price;

    @Column(name = "Stock")
    private Integer stock;

    @Column(name = "ImageUrl")
    private String imageUrl;

    @Column(name = "Status")
    private String status;

    @Column(name = "CreatedAt")
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "CateId")
    private Category category;

}

