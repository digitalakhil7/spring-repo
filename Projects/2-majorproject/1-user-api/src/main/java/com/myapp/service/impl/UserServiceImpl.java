package com.myapp.service.impl;

import com.myapp.dto.ResetPasswordDto;
import com.myapp.dto.ShippingAddressDto;
import com.myapp.dto.UserDto;
import com.myapp.entity.RoleEntity;
import com.myapp.entity.ShippingAddressEntity;
import com.myapp.entity.UserEntity;
import com.myapp.mapper.ShippingAddressMapper;
import com.myapp.mapper.UserMapper;
import com.myapp.repository.RoleRepository;
import com.myapp.repository.ShippingAddressRepository;
import com.myapp.repository.UserRepository;
import com.myapp.service.EmailService;
import com.myapp.service.UserService;
import jdk.jfr.consumer.RecordedStackTrace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private EmailService emailService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private ShippingAddressRepository shippingAddressRepository;

    @Override
    public UserEntity saveUser(UserDto userDto) {

//        userRepository.findByUserEmail(userDto.getUserEmail()).ifPresent(userEntity -> {
//            throw new RuntimeException("Email is already registered");
//        });

        String randomPassword = generateRandomPassword(8);

        UserEntity userEntity = UserMapper.getUserEntity(userDto);
        userEntity.setUserPassword(randomPassword);
        userEntity.setIsPasswordUpdated("NO");
        if(userDto.getRoleName()!=null) {
            roleRepository.findByRoleName(userDto.getRoleName()).ifPresent(roleEntity -> {
                Set<RoleEntity> roles = new HashSet<>();
                roles.add(roleEntity);
                userEntity.setRoles(roles);
            });
        }else{
            throw new RuntimeException("Role not found");
        }
        UserEntity entity = userRepository.save(userEntity);
        emailService.sendSimpleEmail(entity.getUserEmail(), "Account Created", "Your temporary password is: "+randomPassword);

        return entity;
    }

    private String generateRandomPassword(int length) {
        final String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*";
        java.security.SecureRandom random = new java.security.SecureRandom();
        StringBuilder password = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            password.append(chars.charAt(random.nextInt(chars.length())));
        }

        return password.toString();
    }

    @Override
    public UserEntity login(String userEmail, String userPassword) {
        Optional<UserEntity> user = userRepository.findByUserEmailAndUserPassword(userEmail, userPassword);
        if(user.isPresent()){
            return user.get();
        }
        return null;
    }

    @Override
    public ResetPasswordDto resetPassword(ResetPasswordDto resetPasswordDto) {
        Optional<UserEntity> user = userRepository.findByUserEmail(resetPasswordDto.getUserEmail());
        if(user.isPresent() && resetPasswordDto.getUserNewPassword().equals(resetPasswordDto.getUserNewConfirmPassword())){
            UserEntity userEntity = user.get();
            userEntity.setUserPassword(resetPasswordDto.getUserNewPassword());
            userEntity.setIsPasswordUpdated("YES");
            userRepository.save(userEntity);
//            emailService.sendSimpleEmail(userEntity.getUserEmail(), "Password Updated", "Updated password is: "+userEntity.getUserPassword());
            return resetPasswordDto;
        }
        return null;
    }

    @Override
    public ShippingAddressDto saveAddress(Integer userId, ShippingAddressDto shippingAddressDto) {
        Optional<UserEntity> user = userRepository.findById(userId);
        if(user.isPresent()){
            ShippingAddressEntity shippingAddressEntity = ShippingAddressMapper.getShippingAddressEntity(shippingAddressDto);
            shippingAddressEntity.setUserId(user.get());

            return ShippingAddressMapper.getShippingAddressDto(shippingAddressRepository.save(shippingAddressEntity));
        }
        return null;
    }

    @Override
    public ShippingAddressDto deleteAddress(Integer addressId) {
        Optional<ShippingAddressEntity> address = shippingAddressRepository.findById(addressId);
        if(address.isPresent()){
            shippingAddressRepository.deleteById(addressId);
            return ShippingAddressMapper.getShippingAddressDto(address.get());
        }
        return null;
    }

    @Override
    public UserEntity getUserByEmail(String userEmail) {
        return userRepository.findByUserEmail(userEmail).orElse(null);
    }
}
