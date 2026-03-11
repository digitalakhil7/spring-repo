package com.app.service.impl;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dto.DashboardDto;
import com.app.entity.Counsellor;
import com.app.entity.Enquiry;
import com.app.repository.CounsellorRepository;
import com.app.repository.EnquiryRepository;
import com.app.service.CounsellorService;

@Service
public class CounsellorServiceImpl implements CounsellorService{

	@Autowired
	private CounsellorRepository counsellorRepository;
	
	@Autowired
	private EnquiryRepository enquiryRepository;
	
	@Override
	public boolean createCounsellor(Counsellor counsellor) {
		
		if(counsellorRepository.existsByCounsellorEmail(counsellor.getCounsellorEmail()))
			return false;
		else
			counsellorRepository.save(counsellor);
		return true;
	}

	@Override
	public Counsellor login(String counsellorEmail, String counsellorPassword) {
		Optional<Counsellor> counsellor = counsellorRepository.findByCounsellorEmailAndCounsellorPassword(counsellorEmail, counsellorPassword);
		if(counsellor.isPresent())
			return counsellor.get();
		return null;
	}

	@Override
	public DashboardDto getDashboardInfo(Integer counsellorId) {
		List<Enquiry> enquiries = enquiryRepository.findByCounsellorCounsellorId(counsellorId);
		
		Map<String, Long> statusWiseMap = enquiries.stream().collect(Collectors.groupingBy(Enquiry::getEnquiryStatus, Collectors.counting()));
		
		int totalCount = statusWiseMap.size();
		int openCount = statusWiseMap.getOrDefault("open", 0l).intValue();
		int enrolledCount = statusWiseMap.getOrDefault("enrolled", 0l).intValue();
		int lostCount = statusWiseMap.getOrDefault("lost", 0l).intValue();
		
		return DashboardDto.builder()
				.totalEnquiries(totalCount).openEnquiries(openCount).enrolledEnquiries(enrolledCount).lostEnquiries(lostCount).build();
	}
	
}
