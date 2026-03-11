package com.app.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnquiryDto {

    private Integer enquiryId;
    private String studentName;
    private String studentPhNo;
    private String studentClassMode;
    private String enquiryStatus;
    private Integer courseId;
}
