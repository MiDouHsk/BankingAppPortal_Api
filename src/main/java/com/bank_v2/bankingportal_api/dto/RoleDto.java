package com.bank_v2.bankingportal_api.dto;


import com.bank_v2.bankingportal_api.entity.User;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RoleDto {
    private Long id;
    private String roleName;

}
