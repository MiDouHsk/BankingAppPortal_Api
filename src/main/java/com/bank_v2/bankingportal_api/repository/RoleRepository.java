package com.bank_v2.bankingportal_api.repository;

import com.bank_v2.bankingportal_api.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByRoleName(Role.RoleType roleName);
}
