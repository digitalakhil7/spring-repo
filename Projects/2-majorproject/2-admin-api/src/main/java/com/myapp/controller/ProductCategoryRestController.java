package com.myapp.controller;

import com.myapp.dto.ApiResponse;
import com.myapp.entity.ProductCategoryEntity;
import com.myapp.service.ProductCatergoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProductCategoryRestController {
    @Autowired
    private ProductCatergoryService productCatergoryService;

    @PostMapping("/createCategory")
    public ResponseEntity<ApiResponse<ProductCategoryEntity>> createCategory(ProductCategoryEntity productCategoryEntity){
        ProductCategoryEntity entity = productCatergoryService.createCategory(productCategoryEntity);
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setStatusCode(HttpStatus.CREATED.value());
        apiResponse.setData(entity);
        apiResponse.setMessage("Product Created with id: "+entity.getCategoryId());

        return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
    }

    @GetMapping("/getOneCategory/{categoryId}")
    public ResponseEntity<ApiResponse<ProductCategoryEntity>> getOneCategory(@PathVariable Integer categoryId){
        ProductCategoryEntity entity = productCatergoryService.getOneCategory(categoryId);
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setStatusCode(HttpStatus.OK.value());
        apiResponse.setData(entity);
        apiResponse.setMessage("Product Category found with id: "+entity.getCategoryId());

        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping("/getAllCategories")
    public ResponseEntity<ApiResponse> getAllCategories() {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setStatusCode(HttpStatus.OK.value());
        apiResponse.setData(productCatergoryService.getAllCategories());
        apiResponse.setMessage("All Product Categories found");

        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PutMapping("/updateCategory")
    public ResponseEntity<ApiResponse<ProductCategoryEntity>> updateCategory(ProductCategoryEntity productCategoryEntity){
        ProductCategoryEntity entity = productCatergoryService.updateCategory(productCategoryEntity);
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setStatusCode(HttpStatus.OK.value());
        apiResponse.setData(entity);
        apiResponse.setMessage("Product Category updated with id: "+entity.getCategoryId());

        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @DeleteMapping("/deleteOneCategory/{categoryId}")
    public ResponseEntity<ApiResponse<ProductCategoryEntity>> deleteOneCategory(@PathVariable Integer categoryId){
        ProductCategoryEntity entity = productCatergoryService.deleteOneCategory(categoryId);
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setStatusCode(HttpStatus.OK.value());
        apiResponse.setData(entity);
        apiResponse.setMessage("Product Category deleted with id: "+entity.getCategoryId());

        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
}
