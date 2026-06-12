package Meko.MekoApp.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
@Entity
@Table(name = "Products")
public class Product {



        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "Id")
        private Integer id;

        @Column(name = "CateId", nullable = false)
        private Integer cateId;

        @Column(name = "ProductName", nullable = false, length = 200)
        private String productName;

        @Column(name = "Description", columnDefinition = "nvarchar(max)")
        private String description;

        @Column(name = "Price", nullable = false, precision = 18, scale = 2)
        private BigDecimal price;

        @Column(name = "Stock")
        private Integer stock;

        @Column(name = "ImageUrl", length = 255)
        private String imageUrl;

        @Column(name = "Status", length = 20)
        private String status;

        @Column(name = "CreatedAt")
        private LocalDateTime createdAt;

        // Constructors
        public Product() {
        }

        // Getters and Setters
        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Integer getCateId() {
            return cateId;
        }

        public void setCateId(Integer cateId) {
            this.cateId = cateId;
        }

        public String getProductName() {
            return productName;
        }

        public void setProductName(String productName) {
            this.productName = productName;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public BigDecimal getPrice() {
            return price;
        }

        public void setPrice(BigDecimal price) {
            this.price = price;
        }

        public Integer getStock() {
            return stock;
        }

        public void setStock(Integer stock) {
            this.stock = stock;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public LocalDateTime getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
        }
    }

