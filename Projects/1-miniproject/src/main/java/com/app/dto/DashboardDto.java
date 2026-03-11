package com.app.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DashboardDto {
	private Integer totalEnquiries;
	private Integer openEnquiries;
	private Integer enrolledEnquiries;
	private Integer lostEnquiries;
}
