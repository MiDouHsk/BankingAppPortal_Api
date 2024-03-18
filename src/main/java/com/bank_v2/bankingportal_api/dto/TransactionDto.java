package com.bank_v2.bankingportal_api.dto;

import com.bank_v2.bankingportal_api.entity.TransactionType;
import lombok.Data;

import java.util.Date;

@Data
public class TransactionDto {
    private Long id;
    private double amount;
    private TransactionType transaction_type;
    private Date transaction_date;
    private String sourceAccountNumber;
    private String targetAccountNumber;
}
