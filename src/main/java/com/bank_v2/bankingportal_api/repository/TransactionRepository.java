package com.bank_v2.bankingportal_api.repository;

import java.util.List;

import com.bank_v2.bankingportal_api.entity.Transaction;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    @Transactional
    void deleteBySourceAccount_Id(Long sourceAccountId);
    @Transactional
    void deleteByTargetAccount_Id(Long targetAccountId);
    List<Transaction> findBySourceAccount_AccountNumberOrTargetAccount_AccountNumber(String sourceAccountNumber, String targetAccountNumber);
}