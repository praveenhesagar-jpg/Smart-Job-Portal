package com.jobportal.jobportal.controller;

import java.util.List;
import org.springframework.data.domain.Page;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.jobportal.jobportal.entity.Job;
import com.jobportal.jobportal.service.JobService;

@RestController
@RequestMapping("/jobs")
public class JobController {

    @Autowired
    private JobService jobService;

    // Add Job
    @PostMapping("/add")
    public Job addJob(@RequestBody Job job) {
        return jobService.addJob(job);
    }

    // View All Jobs
    @GetMapping("/all")
    public List<Job> getAllJobs() {
        return jobService.getAllJobs();
    }

    // Search by Location
    @GetMapping("/search/location")
    public List<Job> getByLocation(@RequestParam String location) {
        return jobService.searchByLocation(location);
    }

    // Search by Title
    @GetMapping("/search/title")
    public List<Job> getByTitle(@RequestParam String keyword) {
        return jobService.searchByTitle(keyword);
    }
    
 // Update Job
    @PutMapping("/update/{id}")
    public Job updateJob(@PathVariable Long id, @RequestBody Job job) {
        return jobService.updateJob(id, job);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteJob(@PathVariable Long id) {
        return jobService.deleteJob(id);
    }
    
    @GetMapping("/page")
    public Page<Job> getJobsPage(
            @RequestParam int page,
            @RequestParam int size) {
        return jobService.getJobsWithPagination(page, size);
    }
    @GetMapping("/sort")
    public List<Job> sortJobs(@RequestParam String field) {
        return jobService.getJobsSorted(field);
    }
    
}