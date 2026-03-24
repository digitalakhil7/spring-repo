package com.myapp.service;

import com.myapp.dto.ResetPasswordDto;
import com.myapp.dto.ShippingAddressDto;
import com.myapp.dto.UserDto;
import com.myapp.entity.ShippingAddressEntity;
import com.myapp.entity.UserEntity;

public interface UserService {
    UserEntity saveUser(UserDto userDto);
    UserEntity login(String userEmail, String userPassword);
    ResetPasswordDto resetPassword(ResetPasswordDto resetPasswordDto);
    ShippingAddressDto saveAddress(Integer userId, ShippingAddressDto addressDto);
    ShippingAddressDto deleteAddress(Integer addressId);
    UserEntity getUserByEmail(String userEmail);
}
