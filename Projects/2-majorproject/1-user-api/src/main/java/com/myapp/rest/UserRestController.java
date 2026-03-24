package com.myapp.rest;

import com.myapp.dto.ApiResponse;
import com.myapp.dto.ResetPasswordDto;
import com.myapp.dto.ShippingAddressDto;
import com.myapp.dto.UserDto;
import com.myapp.entity.UserEntity;
import com.myapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserRestController {

    @Autowired
    private UserService userService;

    @PostMapping("/saveUser")
    public ResponseEntity<ApiResponse<UserEntity>> saveUser(UserDto userDto){
        UserEntity userEntity = userService.saveUser(userDto);

        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("User Created");
        apiResponse.setStatusCode(HttpStatus.CREATED.value());
        apiResponse.setData(userEntity);
        return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<UserEntity>> login(String userEmail, String userPassword){
        UserEntity entity = userService.login(userEmail, userPassword);
        ApiResponse reponse = new ApiResponse();

        if(entity == null){
            reponse.setStatusCode(HttpStatus.UNAUTHORIZED.value());
            reponse.setMessage("Invalid email or password");
            reponse.setData(null);
            return new ResponseEntity<>(reponse, HttpStatus.UNAUTHORIZED);
        }

        reponse.setStatusCode(HttpStatus.OK.value());
        reponse.setMessage("User created successfully");
        reponse.setData(entity);

        return new ResponseEntity<>(reponse, HttpStatus.OK);
    }

    @PostMapping("/resetPassword")
    public ResponseEntity<ApiResponse> resetPassword(ResetPasswordDto resetPasswordDto){
        ResetPasswordDto responseDto = userService.resetPassword(resetPasswordDto);
        ApiResponse apiResponse = new ApiResponse();

        if(responseDto==null){
            apiResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
            apiResponse.setMessage("Check email and password and try again");
            apiResponse.setData(null);
            return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
        }

        apiResponse.setStatusCode(HttpStatus.OK.value());
        apiResponse.setMessage("Password reset successfully");
        apiResponse.setData(responseDto);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping("/getUserByEmail")
    public ResponseEntity<ApiResponse<UserEntity>> getUserByEmail(String userEmail) {
        UserEntity userEntity = userService.getUserByEmail(userEmail);
        ApiResponse apiResponse = new ApiResponse();

        if (userEntity == null) {
            apiResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
            apiResponse.setMessage("User not found with email: " + userEmail);
            apiResponse.setData(null);
            return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
        }

        apiResponse.setStatusCode(HttpStatus.OK.value());
        apiResponse.setMessage("User retrieved successfully");
        apiResponse.setData(userEntity);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PostMapping("/saveAddress/{userId}")
    public ResponseEntity<ApiResponse> saveAddress(@PathVariable Integer userId, ShippingAddressDto shippingAddressDto){
        ShippingAddressDto responseDto = userService.saveAddress(userId, shippingAddressDto);
        ApiResponse apiResponse = new ApiResponse();

        if(responseDto==null){
            apiResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
            apiResponse.setMessage("User not found with id: "+userId);
            apiResponse.setData(null);
            return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
        }

        apiResponse.setStatusCode(HttpStatus.OK.value());
        apiResponse.setMessage("Address saved successfully");
        apiResponse.setData(responseDto);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PostMapping("/deleteAddress/{addressId}")
    public ResponseEntity<ApiResponse> deleteAddress(@PathVariable Integer addressId) {
        ShippingAddressDto responseDto = userService.deleteAddress(addressId);
        ApiResponse apiResponse = new ApiResponse();

        if (responseDto == null) {
            apiResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
            apiResponse.setMessage("Address not found with id: " + addressId);
            apiResponse.setData(null);
            return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
        }

        apiResponse.setStatusCode(HttpStatus.OK.value());
        apiResponse.setMessage("Address deleted successfully");
        apiResponse.setData(responseDto);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
}
