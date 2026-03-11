package com.app.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "counsellor")
@Getter
@Setter
public class Counsellor {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "counsellor_id")
	private Integer counsellorId;

	private String counsellorName;

	private String counsellorEmail;

	private String counsellorPassword;

	private Long counsellorPhNo;
	
	@CreationTimestamp
	private LocalDateTime counsellorCreatedDate;
	
	@UpdateTimestamp
	private LocalDateTime counsellorUpdatedDate;
}
