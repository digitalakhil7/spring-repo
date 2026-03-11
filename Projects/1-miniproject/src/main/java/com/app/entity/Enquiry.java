package com.app.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="enquiry")
@Getter
@Setter
public class Enquiry {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="enquiry_id")
	private Integer enquiryId;
	
	private String studentName;
	
	private Long studentPhNo;
	
	private String studentClassMode;
	
	private String studentCourse;
	
	private String studentStatus;
	
	@ManyToOne
	@JoinColumn(name = "course_id")
	private Course course;
	
	@ManyToOne
	@JoinColumn(name = "counsellor_id")
	private Counsellor counsellor;
	
	private LocalDateTime enquiryCreatedDate;
	
	private LocalDateTime enquiryUpdatedDate;
}
