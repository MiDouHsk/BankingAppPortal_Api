package com.bank_v2.bankingportal_api.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

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


}
