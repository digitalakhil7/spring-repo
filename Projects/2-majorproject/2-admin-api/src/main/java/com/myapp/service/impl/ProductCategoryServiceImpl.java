package com.myapp.service.impl;

import com.myapp.entity.ProductCategoryEntity;
import com.myapp.repository.ProductCategoryRepository;
import com.myapp.service.ProductCatergoryService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductCategoryServiceImpl implements ProductCatergoryService {
    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @Override
    public ProductCategoryEntity createCategory(ProductCategoryEntity productCategoryEntity) {
        if(productCategoryEntity.getCategoryId()==null)
        return productCategoryRepository.save(productCategoryEntity);

        return null;
    }

    @Override
    public ProductCategoryEntity getOneCategory(Integer categoryId) {
        ProductCategoryEntity entity = productCategoryRepository.findById(categoryId)
                .orElseThrow(()-> new RuntimeException("product not found with id: "+categoryId));
        return entity;
    }

    @Override
    public List<ProductCategoryEntity> getAllCategories() {
        return productCategoryRepository.findAll();
    }

    @Override
    public ProductCategoryEntity updateCategory(ProductCategoryEntity productCategoryEntity) {
        if(productCategoryEntity.getCategoryId()!=null) {
            getOneCategory(productCategoryEntity.getCategoryId()); // to check if category exists or not
            return productCategoryRepository.save(productCategoryEntity);
        }
        return null;
    }

    @Override
    public ProductCategoryEntity deleteOneCategory(Integer categoryId) {
        ProductCategoryEntity entity = getOneCategory(categoryId);
        if(entity!=null){
            entity.setIsActive("NO");
            productCategoryRepository.save(entity);
            return entity;
        }
        return null;
    }
}

