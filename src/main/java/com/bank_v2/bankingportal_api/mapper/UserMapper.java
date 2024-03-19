package com.bank_v2.bankingportal_api.mapper;

import com.bank_v2.bankingportal_api.dto.UserDto;
import com.bank_v2.bankingportal_api.entity.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    private final ModelMapper modelMapper;

    @Autowired
    public UserMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public User mapToEntity(UserDto userDto) {
        return modelMapper.map(userDto, User.class);
    }

    public UserDto maptoDto(User user) {
        return modelMapper.map(user, UserDto.class);
    }
}
