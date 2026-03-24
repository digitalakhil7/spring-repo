package com.myapp.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "user")
@Setter
@Getter
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id")
    private Integer userId;
    private String userName;
    private String userEmail;
    private String userPassword;
    private Long userPhoneNo;
    private String isPasswordUpdated;
    @CreationTimestamp
    private LocalDate userCreatedDate;
    @UpdateTimestamp
    private LocalDate userUpdatedDate;

    @ManyToMany
    @JoinTable(name="user_role",
    joinColumns = @JoinColumn(name = "user_id"),
    inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<RoleEntity> roles;
}
