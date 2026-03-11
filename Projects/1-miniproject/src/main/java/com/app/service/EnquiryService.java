package com.app.service;

import java.util.List;

import com.app.dto.EnquiryDto;
import com.app.dto.EnquiryFilterDto;
import com.app.entity.Enquiry;

public interface EnquiryService {
	boolean createEnquiry(EnquiryDto enquiryDto, Integer counsellorId);
	List<Enquiry> getEnquiriesByCounsellorId(Integer counsellorId);
	List<Enquiry> getFilteredEnquiriesByCounsellorId(EnquiryFilterDto enquiryFilterDto,Integer counsellorId);
	Enquiry getEnquiryById(Integer enquiryId);
	boolean updateEnquiryStatus(EnquiryDto enquiryDto);
}
