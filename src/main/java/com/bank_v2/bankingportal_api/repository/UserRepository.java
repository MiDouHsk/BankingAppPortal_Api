package com.bank_v2.bankingportal_api.repository;

import com.bank_v2.bankingportal_api.entity.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
    User findByAccountAccountNumber(String accountNumber);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM Account a WHERE a.user.id = ?1")
    void deleteAccountsById(Long userId);
    @Transactional
//    @Modifying
//    @Query(value = "DELETE FROM User u WHERE u.id = ?1", nativeQuery = true)
    void deleteUserWithById(Long id);
}
