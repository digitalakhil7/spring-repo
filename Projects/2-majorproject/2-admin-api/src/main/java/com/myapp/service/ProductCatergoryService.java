package com.myapp.service;

import com.myapp.entity.ProductCategoryEntity;
import com.myapp.entity.ProductEntity;

import java.util.List;

public interface ProductCatergoryService {

    ProductCategoryEntity createCategory(ProductCategoryEntity productCategoryEntity);
    ProductCategoryEntity getOneCategory(Integer categoryId);
    List<ProductCategoryEntity> getAllCategories();
    ProductCategoryEntity updateCategory(ProductCategoryEntity productCategoryEntity);
    ProductCategoryEntity deleteOneCategory(Integer categoryId);
}
