package com.bank_v2.bankingportal_api.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
public class UserDto {
    private String name;
    private String email;
    private String address;
    private String password;
    private String phone_number;
    private String accountNumber;
    private String IFSC_code;
    private String branch;
    private String account_type;
    private AccountDto account;
}
