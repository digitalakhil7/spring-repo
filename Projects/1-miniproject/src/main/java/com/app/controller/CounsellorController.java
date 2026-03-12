package com.app.controller;

import com.app.dto.DashboardDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
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
		return "register";
	}

	@GetMapping("/login")
	public String loginPage(Model model){
		model.addAttribute("counsellor", new Counsellor());
		return "login";
	}

	@PostMapping("/login")
	public String loginUser(Model model, @ModelAttribute Counsellor counsellor, HttpServletRequest request){

		Counsellor counsellor1 = counsellorService.login(counsellor.getCounsellorEmail(), counsellor.getCounsellorPassword());

		if(counsellor1==null){
			model.addAttribute("errorMsg", "Invalid email and password");
			return "login";
		}

		HttpSession session = request.getSession(true);
		session.setAttribute("cid", counsellor1.getCounsellorId());

		return "redirect:/dashboard";
	}

	@GetMapping("/dashboard")
	public String showDashboard(HttpServletRequest request, Model model){
		HttpSession session = request.getSession(false);
		Integer cid = (Integer)session.getAttribute("cid");
		DashboardDto dashboardInfo = counsellorService.getDashboardInfo(cid);
		model.addAttribute("dashboardInfo", dashboardInfo);
		return "dashboard";
	}

	@GetMapping("/logout")
	public String logout(HttpServletRequest request){
		HttpSession session = request.getSession(false);
		session.invalidate();
		return "redirect:login";
	}
}
