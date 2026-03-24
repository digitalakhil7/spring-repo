package com.myapp.dto;

import lombok.Data;

@Data
public class ResetPasswordDto {
    private String userEmail;
    private String userNewPassword;
    private String userNewConfirmPassword;
}
