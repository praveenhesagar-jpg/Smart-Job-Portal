package com.jobportal.jobportal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.jobportal.jobportal.entity.Application;
import java.util.List;

public interface ApplicationRepository extends JpaRepository<Application, Long> {

    List<Application> findByApplicantEmail(String email); // ✅ inside interface
}