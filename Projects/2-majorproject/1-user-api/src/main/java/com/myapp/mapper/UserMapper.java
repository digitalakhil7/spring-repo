package com.myapp.mapper;

import com.myapp.dto.UserDto;
import com.myapp.entity.UserEntity;
import org.modelmapper.ModelMapper;

public class UserMapper {
    private static final ModelMapper mapper = new ModelMapper();

    public static UserEntity getUserEntity(UserDto userDto){
        return mapper.map(userDto, UserEntity.class);
    }

    public static UserDto getUserDto(UserEntity userEntity){
        return mapper.map(userEntity, UserDto.class);
    }
}
