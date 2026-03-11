package com.app.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class DashboardDto {
	private Integer totalEnquiries;
	private Integer openEnquiries;
	private Integer enrolledEnquiries;
	private Integer lostEnquiries;
}
