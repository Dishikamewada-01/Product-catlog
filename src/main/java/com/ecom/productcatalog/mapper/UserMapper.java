package com.ecom.productcatalog.mapper;

import com.ecom.productcatalog.dto.response.UserDto;
import com.ecom.productcatalog.model.Users;

public class UserMapper {
    public static UserDto toDto(Users user) {
        return new UserDto(user.getUsername(), user.getEmail());
    }
}
