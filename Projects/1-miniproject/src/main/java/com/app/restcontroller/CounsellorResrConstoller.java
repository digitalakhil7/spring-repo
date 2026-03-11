package com.app.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.app.entity.Counsellor;
import com.app.service.CounsellorService;

@RestController
public class CounsellorResrConstoller {
	
	@Autowired
	private CounsellorService counsellorService;
	
	@PostMapping("/saveConsellor")
	public ResponseEntity<String> saveConseollor(@RequestBody Counsellor counsellor){
		boolean isCousellorCreated = counsellorService.createCounsellor(counsellor);
		
		return new ResponseEntity<>("Counsellor created: "+isCousellorCreated,HttpStatus.OK);
	}
}
