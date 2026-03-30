package com.jobportal.jobportal.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort; // ✅ correct one

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jobportal.jobportal.entity.Job;
import com.jobportal.jobportal.repository.JobRepository;
@Service
public class JobService {

    @Autowired
    private JobRepository jobRepository;

    // Add Job
    public Job addJob(Job job) {
        return jobRepository.save(job);
    }

    // Get All Jobs
    public List<Job> getAllJobs() {
        return jobRepository.findAll();
    }

    // Search by Location
    public List<Job> searchByLocation(String location) {
        return jobRepository.findByLocation(location);
    }

    // Search by Title
    public List<Job> searchByTitle(String keyword) {
        return jobRepository.findByTitleContaining(keyword);
    }

    // Update Job
    public Job updateJob(Long id, Job updatedJob) {
        Job job = jobRepository.findById(id).orElse(null);

        if (job != null) {
            job.setTitle(updatedJob.getTitle());
            job.setCompany(updatedJob.getCompany());
            job.setLocation(updatedJob.getLocation());
            job.setDescription(updatedJob.getDescription());
            job.setSalary(updatedJob.getSalary());
            return jobRepository.save(job);
        }
        return null;
    }

    // Delete Job
    public String deleteJob(Long id) {
        if (jobRepository.existsById(id)) {
            jobRepository.deleteById(id);
            return "Job deleted successfully";
        } else {
            return "Job not found";
        }
    }

    // ✅ Pagination (separate method)
    public Page<Job> getJobsWithPagination(int page, int size) {
        return jobRepository.findAll(PageRequest.of(page, size));
    }
    public List<Job> getJobsSorted(String field) {
        return jobRepository.findAll(Sort.by(Sort.Direction.ASC, field));
    }
}