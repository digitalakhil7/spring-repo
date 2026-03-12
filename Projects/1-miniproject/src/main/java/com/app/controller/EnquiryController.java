package com.app.controller;

import com.app.dto.EnquiryDto;
import com.app.dto.EnquiryFilterDto;
import com.app.entity.Enquiry;
import com.app.repository.CourseRepository;
import com.app.service.EnquiryService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class EnquiryController {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private EnquiryService enquiryService;

    @GetMapping("/add-enquiry")
    public String enquiryPage(Model model){
        model.addAttribute("enquiry", new EnquiryDto());
        model.addAttribute("courses", courseRepository.findAll());
        return "enquiry";
    }

    @PostMapping("/add-enquiry")
    public String createEnquiry(EnquiryDto enquiryDto, Model model, HttpServletRequest request){
        HttpSession session = request.getSession(false);
        Integer cid = (Integer) session.getAttribute("cid");
        boolean isEnquirySaved = enquiryService.createEnquiry(enquiryDto, cid);
        if(isEnquirySaved)
            model.addAttribute("successMsg", "Enquiry saved");
        else
            model.addAttribute("errorMsg", "Enquiry not saved");
        model.addAttribute("enquiry", new EnquiryDto());
        model.addAttribute("courses", courseRepository.findAll());
        return "enquiry";
    }

    @GetMapping("/view-enquiries")
    public String viewEnquiries(Model model, HttpServletRequest request){
        HttpSession session = request.getSession();
        Integer cid = (Integer) session.getAttribute("cid");
        List<Enquiry> enquiries = enquiryService.getEnquiriesByCounsellorId(cid);
        model.addAttribute("enquiries", enquiries);
        model.addAttribute("enquiryfilter", new EnquiryFilterDto());
        model.addAttribute("courses", courseRepository.findAll());
        return "view-enquiries";
    }

    @PostMapping("/view-enquiries")
    public String saveEnquiries(EnquiryFilterDto enquiryFilterDto, Model model, HttpServletRequest request){
        HttpSession session = request.getSession();
        Integer cid = (Integer) session.getAttribute("cid");
        List<Enquiry> enquiries = enquiryService.getFilteredEnquiriesByCounsellorId(enquiryFilterDto, cid);
        model.addAttribute("enquiries", enquiries);
        model.addAttribute("enquiryfilter", enquiryFilterDto);
        model.addAttribute("courses", courseRepository.findAll());
        return "view-enquiries";
    }
}
