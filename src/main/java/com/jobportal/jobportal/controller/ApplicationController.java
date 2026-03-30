package com.jobportal.jobportal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.jobportal.jobportal.entity.Application;
import com.jobportal.jobportal.entity.Job;
import com.jobportal.jobportal.service.ApplicationService;

@RestController
@RequestMapping("/applications")
@CrossOrigin
public class ApplicationController {

    @Autowired
    private ApplicationService applicationService;

    // ✅ APPLY WITH FILE
    @PostMapping("/applyWithFile")
    public Application applyWithFile(
            @RequestParam String email,
            @RequestParam Long jobId,
            @RequestParam("file") MultipartFile file) throws Exception {

        Application app = new Application();
        app.setApplicantEmail(email);

        app.setResume(file.getBytes());
        app.setFileName(file.getOriginalFilename());

        Job job = new Job();
        job.setId(jobId);
        app.setJob(job);

        return applicationService.applyJob(app);
    }

    // ✅ MY APPLICATIONS
    @GetMapping("/my")
    public List<Application> getMyApplications(@RequestParam String email) {
        return applicationService.getApplicationsByEmail(email);
    }

    // ✅ ALL APPLICATIONS
    @GetMapping("/all")
    public List<Application> getAllApplications() {
        return applicationService.getAllApplications();
    }

    // ✅ UPDATE STATUS
    @PutMapping("/status/{id}")
    public Application updateStatus(@PathVariable Long id, @RequestParam String status) {
        return applicationService.updateStatus(id, status);
    }

    // ✅ DOWNLOAD RESUME (FINAL FIX)
    @GetMapping("/resume/{id}")
    public ResponseEntity<byte[]> downloadResume(@PathVariable Long id) {

        Application app = applicationService.getById(id);

        if (app == null || app.getResume() == null) {
            return ResponseEntity.ok()
                    .header("Content-Type", "text/plain")
                    .body("No Resume Uploaded".getBytes());
        }

        return ResponseEntity.ok()
                .header("Content-Disposition", "attachment; filename=" + app.getFileName())
                .header("Content-Type", "application/pdf")
                .body(app.getResume());
    }
}