package com.app.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;

import com.app.dto.EnquiryDto;
import com.app.dto.EnquiryFilterDto;
import com.app.entity.Counsellor;
import com.app.entity.Course;
import com.app.entity.Enquiry;
import com.app.repository.CounsellorRepository;
import com.app.repository.CourseRepository;
import com.app.repository.EnquiryRepository;
import com.app.service.EnquiryService;

public class EnquiryServiceImpl implements EnquiryService {
	
	@Autowired
	private EnquiryRepository enquiryRepository;
	
	@Autowired
	private CourseRepository courseRepository;
	
	@Autowired
	private CounsellorRepository counsellorRepository;

	@Override
	public boolean createEnquiry(EnquiryDto enquiryDto, Integer counsellorId) {
		Counsellor consellor = counsellorRepository.findByCounsellorId(counsellorId).orElseThrow();
		Course course = courseRepository.findByCourseId(enquiryDto.getCourseId()).orElseThrow();
		
		Enquiry enquiry = new Enquiry();
		
		BeanUtils.copyProperties(enquiryDto, enquiry);
		enquiry.setCounsellor(consellor);
		enquiry.setCourse(course);
		enquiryRepository.save(enquiry);
		
		return true;
	}

	@Override
	public List<Enquiry> getEnquiriesByCounsellorId(Integer counsellorId) {
		return enquiryRepository.findByCounsellorCounsellorId(counsellorId);
	}

	@Override
	public List<Enquiry> getFilteredEnquiriesByCounsellorId(EnquiryFilterDto enquiryFilterDto, Integer counsellorId) {
		
		Counsellor counsellor = counsellorRepository.findByCounsellorId(counsellorId).orElseThrow();
		Course course = courseRepository.findByCourseId(enquiryFilterDto.getCourseId()).orElseThrow();
		
		Enquiry enquiry = new Enquiry();
		enquiry.setStudentClassMode(enquiryFilterDto.getStudentClassMode());
		enquiry.setCourse(course);
		enquiry.setEnquiryStatus(enquiryFilterDto.getEnquiryStatus());
		enquiry.setCounsellor(counsellor);
		
		return enquiryRepository.findAll(Example.of(enquiry));
	}

	@Override
	public Enquiry getEnquiryById(Integer enquiryId) {
		return enquiryRepository.findById(enquiryId).orElseThrow();
	}

	@Override
	public boolean updateEnquiryStatus(EnquiryDto enquiryDto) {
		Optional<Enquiry> enquiry = enquiryRepository.findById(enquiryDto.getEnquiryId());
		
		if(enquiry.isPresent()) {
			Enquiry enq = enquiry.get();
			enq.setEnquiryStatus(enquiryDto.getEnquiryStatus());
			enquiryRepository.save(enq);
			return true;
		}
			
		return false;
	}

}
