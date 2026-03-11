package com.app.service;

import com.app.dto.DashboardDto;
import com.app.entity.Counsellor;

public interface CounsellorService {
	boolean createCounsellor(Counsellor counsellor);
	Counsellor login(String counsellorEmail, String counsellorPassword);
	DashboardDto getDashboardInfo(Integer counsellorId);
}
