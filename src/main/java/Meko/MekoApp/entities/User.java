package Meko.MekoApp.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "Users")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Integer id;

    @Column(name = "Username")
    private String username;

    @Column(name = "PasswordHash")
    private String password;

    @Column(name = "Email")
    private String email;

    @Column(name = "Phone")
    private String phone;

    @Column(name = "Role")
    private String role;

    @Column(name = "CreatedAt")
    private LocalDateTime createdAt;
}
