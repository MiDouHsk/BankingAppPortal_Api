package com.bank_v2.bankingportal_api.mapper;

import com.bank_v2.bankingportal_api.dto.RoleDto;
import com.bank_v2.bankingportal_api.entity.Role;
import com.bank_v2.bankingportal_api.entity.RoleType;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class RoleMapper {
    private static final ModelMapper modelMapper = new ModelMapper();

    public static RoleDto toDto(RoleType role) {
        RoleDto roleDto = modelMapper.map(role, RoleDto.class);
        return roleDto;
    }

    public static Role toEntity(RoleDto roleDto) {
        Role role = modelMapper.map(roleDto, Role.class);
        return role;
    }

    public Role mapToEntity(RoleType roleType) {
        Role role = new Role();
        role.setRoleType(roleType);
        return role;
    }


}
