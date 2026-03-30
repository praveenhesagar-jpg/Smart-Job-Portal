package com.jobportal.jobportal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.jobportal.jobportal.entity.Job;
import java.util.List;
public interface JobRepository extends JpaRepository<Job, Long> {
	List<Job> findByLocation(String location);
	List<Job> findByTitleContaining(String keyword);	
}