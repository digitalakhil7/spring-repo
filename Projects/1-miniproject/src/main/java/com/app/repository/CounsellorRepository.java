package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.entity.Counsellor;

@Repository
public interface CounsellorRepository extends JpaRepository<Counsellor, Integer>{
	 boolean existsByCounsellorEmail(String email);
}
