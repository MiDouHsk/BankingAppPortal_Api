package com.bank_v2.bankingportal_api.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;
import java.util.Random;


@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String accountNumber;
    private double balance;
    private String account_type = "USER";
    private String branch = "NIT";
    private String IFSC_code = "NIT001";
    private String Pin ;
    private String accountstatus;

    @OneToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

}
