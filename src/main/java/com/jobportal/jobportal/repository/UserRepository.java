package com.jobportal.jobportal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.jobportal.jobportal.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

	
	User findByEmail(String email);
}
