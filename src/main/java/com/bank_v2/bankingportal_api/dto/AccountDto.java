package com.bank_v2.bankingportal_api.dto;

import lombok.Data;

import java.util.Date;

@Data
public class AccountDto {
    private String accountNumber;
    private double balance;
    private String accountType;
    private String branch;
    private String IFSCCode;
    private Date transactionDate;

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }
}
