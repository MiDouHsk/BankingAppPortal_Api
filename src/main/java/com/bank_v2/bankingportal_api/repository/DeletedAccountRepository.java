package com.bank_v2.bankingportal_api.repository;

import com.bank_v2.bankingportal_api.entity.DeleteAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeletedAccountRepository extends JpaRepository<DeleteAccount, Long> {
}
