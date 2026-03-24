package com.myapp.dto;

import lombok.Data;

import java.time.LocalDate;
@Data
public class UserDto {
//    private Integer userId;
    private String userName;
    private String userEmail;
//    private String userPassword;
    private Long userPhoneNo;
//    private LocalDate userCreatedDate;
//    private LocalDate userUpdatedDate;
    private String roleName;
}
