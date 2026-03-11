package com.app.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.entity.Counsellor;
import com.app.repository.CounsellorRepository;
import com.app.service.CounsellorService;

@Service
public class CounsellorServiceImpl implements CounsellorService{

	@Autowired
	private CounsellorRepository counsellorRepository;
	
	@Override
	public boolean createCounsellor(Counsellor counsellor) {
		
		if(counsellorRepository.existsByCounsellorEmail(counsellor.getCounsellorEmail()))
			return false;
		else
			counsellorRepository.save(counsellor);
		return true;
	}
	
}
