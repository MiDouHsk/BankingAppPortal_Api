package com.bank_v2.bankingportal_api.exception;

public class UserValidation extends RuntimeException{
    public UserValidation(String message) {
        super(message);
    }
}
