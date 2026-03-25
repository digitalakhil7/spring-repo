package com.myapp.repository;

import com.myapp.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    Optional<UserEntity> findByUserEmailAndUserPassword(String userEmail, String userPassword);
    Optional<UserEntity> findByUserEmail(String userEmail);
}

