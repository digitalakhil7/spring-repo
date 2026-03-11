package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.app.entity.Counsellor;
import com.app.service.CounsellorService;

@Controller
public class CounsellorController {
	
	@Autowired
	private CounsellorService counsellorService;
	
	@GetMapping("/register")
	public String registerPage(Model model) {
		model.addAttribute("counsellor", new Counsellor());
		return "register";
	}
	
	@PostMapping("/register")
	public String registerCounsellor(@ModelAttribute Counsellor counsellor, Model model) {
		boolean isCounsellorCreated = counsellorService.createCounsellor(counsellor);
		model.addAttribute("successMsg","Counsellor status created: "+isCounsellorCreated);
		counsellorService.createCounsellor(counsellor);
		return "register";
	}
}
