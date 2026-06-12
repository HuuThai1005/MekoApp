package Meko.MekoApp.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "Categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Integer id;

    @Column(name = "CategoryName")
    private String categoryName;

    @Column(name = "Description")
    private String description;
}