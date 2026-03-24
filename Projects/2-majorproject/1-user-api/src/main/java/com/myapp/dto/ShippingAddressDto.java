package com.myapp.dto;

import lombok.Data;

@Data
public class ShippingAddressDto {
    private Integer addressId;
    private String houseNum;
    private String city;
    private String state;
    private Integer zipCode;
    private String country;
    private String addressType;
}
