package com.myapp.runner;

import com.myapp.entity.ProductCategoryEntity;
import com.myapp.repository.ProductCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;

@Component
public class MyRunner implements CommandLineRunner {

    @Autowired
    private ProductCategoryRepository repo;
    @Override
    public void run(String... args) throws Exception {
        System.out.println("----------------------------------------");

        ProductCategoryEntity entity = new ProductCategoryEntity();
        entity.setCategoryName("Cosmetics");

        Example<ProductCategoryEntity> ex = Example.of(entity);

        System.out.println(repo.findAll(ex));
    }
}
