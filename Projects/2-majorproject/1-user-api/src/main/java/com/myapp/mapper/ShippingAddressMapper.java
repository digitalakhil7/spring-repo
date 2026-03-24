package com.myapp.mapper;

import com.myapp.dto.ShippingAddressDto;
import com.myapp.entity.ShippingAddressEntity;
import org.modelmapper.ModelMapper;

public class ShippingAddressMapper {
    private static final ModelMapper mapper = new ModelMapper();

    public static ShippingAddressDto getShippingAddressDto(ShippingAddressEntity shippingAddressEntity){
        return mapper.map(shippingAddressEntity, ShippingAddressDto.class);
    }

    public static ShippingAddressEntity getShippingAddressEntity(ShippingAddressDto shippingAddressDto){
        return mapper.map(shippingAddressDto, ShippingAddressEntity.class);
    }
}
