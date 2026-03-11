package com.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.entity.Counsellor;

@Repository
public interface CounsellorRepository extends JpaRepository<Counsellor, Integer>{
	 boolean existsByCounsellorEmail(String email);
	 Optional<Counsellor> findByCounsellorId(Integer counsellorId);
	 
	 Optional<Counsellor> findByCounsellorEmailAndCounsellorPassword(String counsellorEmail, String counsellorPassword);
	 boolean existsByCounsellorEmailAndCounsellorPassword(String counsellorEmail, String counsellorPassword);
}
