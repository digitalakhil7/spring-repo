package com.myapp.dto;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
@Data
public class ProductCategoryDto {
    private Integer categoryId;
    private String categoryName;
    private String categoryDescription;
    private String isActive;
    private Integer createdBy;
    private Integer updatedBy;
    private LocalDate createdDate;
    private LocalDate updatedDate;
}
