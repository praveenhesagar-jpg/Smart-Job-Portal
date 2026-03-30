package com.jobportal.jobportal.entity;

import jakarta.persistence.*;

@Entity
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String applicantEmail;

    @ManyToOne
    @JoinColumn(name = "job_id")
    private Job job;

    private String status = "PENDING";

    // ✅ FILE DATA (ONLY THIS ONE FIELD)
    @Lob
    @Column(name = "resume_data")
    private byte[] resume;

    private String fileName;

    // ===== GETTERS & SETTERS =====

    public Long getId() {
        return id;
    }

    public String getApplicantEmail() {
        return applicantEmail;
    }

    public void setApplicantEmail(String applicantEmail) {
        this.applicantEmail = applicantEmail;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public byte[] getResume() {
        return resume;
    }

    public void setResume(byte[] resume) {
        this.resume = resume;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}