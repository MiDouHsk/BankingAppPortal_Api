package com.bank_v2.bankingportal_api.dto;

import lombok.Data;

@Data
public class AccountDto {
    private String accountNumber;
    private double balance;
    private String accountType;
    private String branch;
    private String IFSCCode;
}
