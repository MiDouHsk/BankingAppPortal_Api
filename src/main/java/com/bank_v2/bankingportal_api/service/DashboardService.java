package com.bank_v2.bankingportal_api.service;

import com.bank_v2.bankingportal_api.dto.AccountDto;
import com.bank_v2.bankingportal_api.dto.UserDto;

public interface DashboardService {
    UserDto getUserDetails (String accountNumber) throws ClassNotFoundException;
    AccountDto getAccountDetails (String accountNumber) throws ClassNotFoundException;
}
