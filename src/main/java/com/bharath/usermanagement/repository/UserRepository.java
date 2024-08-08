package com.bharath.usermanagement.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bharath.usermanagement.entity.UserDetail;

public interface UserRepository extends JpaRepository<UserDetail, Long>{



public Optional<UserDetail> findByEmail(String email);

Optional<UserDetail> findByEmailOrPhoneNumber(String username, String username2);

public Optional<UserDetail> findByPhoneNumber(String phoneNumber);

public Optional<UserDetail> findByPassword(String password);



}
