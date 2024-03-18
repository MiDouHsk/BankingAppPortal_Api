package com.bank_v2.bankingportal_api.repository;

import com.bank_v2.bankingportal_api.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Account findByAccountNumber(String accountNumber);
}
