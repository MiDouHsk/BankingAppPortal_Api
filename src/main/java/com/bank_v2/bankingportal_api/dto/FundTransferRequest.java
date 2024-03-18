package com.bank_v2.bankingportal_api.dto;

import lombok.Data;

@Data
public class FundTransferRequest {
    private String sourceAccountNumber;
    private String sourceAccountPin;
    private String targetAccountNumber;
    private double amount;
    private String pin;
}
