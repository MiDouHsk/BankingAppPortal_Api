package com.bank_v2.bankingportal_api.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest {
    private String accountNumber;
    private String password;
}
