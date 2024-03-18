package com.bank_v2.bankingportal_api.repository;

import com.bank_v2.bankingportal_api.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);
    User findByAccountAccountNumber(String accountNumber);
}
