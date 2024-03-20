package com.bank_v2.bankingportal_api.service;

public interface AuthenticationService {
    boolean authenticate(String account, String password);
}
