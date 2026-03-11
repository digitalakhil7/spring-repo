package com.app.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnquiryFilterDto {
	private String studentClassMode;
	private Integer courseId;
	private String enquiryStatus;
}
