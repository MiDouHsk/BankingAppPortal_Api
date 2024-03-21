package com.bank_v2.bankingportal_api.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class AccountDto {
    private String accountNumber;
    private double balance;
    private String accountType;
    private String branch;
    private String IFSCCode;
    private Date transactionDate;

//    public Date getTransactionDate() {
//        return transactionDate;
//    }
//
//    public void setTransactionDate(Date transactionDate) {
//        this.transactionDate = transactionDate;
//    }
}
