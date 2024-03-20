package com.bank_v2.bankingportal_api.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "bank_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String password;
    @Column(unique = true)
    private String email;
    private String address;
    private String phone_number;
    private int otpRetryCount;
    private LocalDateTime lastOtpRequestTime;
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Account account;
    @CreationTimestamp
    private LocalDateTime createAt;
    @UpdateTimestamp
    private LocalDateTime updateAt;
    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "role_id")
    private Role role;

}
