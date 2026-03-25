package com.myapp.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "shipping_address")
@Setter
@Getter
public class ShippingAddressEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    private Integer addressId;

    private String houseNum;

    private String city;
    private String state;
    private Integer zipCode;
    private String country;
    private String addressType;

    @ManyToOne
    @JoinColumn(name="user_id")
    private UserEntity userId;
}
