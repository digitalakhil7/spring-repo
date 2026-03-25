package com.myapp.mapper;

import com.myapp.dto.ProductCategoryDto;
import com.myapp.entity.ProductCategoryEntity;
import org.modelmapper.ModelMapper;

public class ProductCategoryMapper {
    private static final ModelMapper modelMapper = new ModelMapper();

    public static ProductCategoryEntity toEntity(ProductCategoryDto dto) {
        return modelMapper.map(dto, ProductCategoryEntity.class);
    }

    public static ProductCategoryDto toDto(ProductCategoryEntity entity) {
        return modelMapper.map(entity, ProductCategoryDto.class);
    }
}
