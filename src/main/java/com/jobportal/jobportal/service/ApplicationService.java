package com.jobportal.jobportal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jobportal.jobportal.entity.Application;
import com.jobportal.jobportal.repository.ApplicationRepository;
import java.util.List;
@Service
public class ApplicationService {

    @Autowired
    private ApplicationRepository applicationRepository;

    public Application applyJob(Application application) {
        return applicationRepository.save(application);
    }
    
    public List<Application> getApplicationsByEmail(String email) {
        return applicationRepository.findByApplicantEmail(email);
    } 
    
    public List<Application> getAllApplications() {
        return applicationRepository.findAll();
    }
    
    public Application updateStatus(Long id, String status) {
        Application app = applicationRepository.findById(id).orElse(null);

        if (app != null) {
            app.setStatus(status);
            return applicationRepository.save(app);
        }

        return null;
    }

    public Application getById(Long id) {
        return applicationRepository.findById(id).orElse(null);
    }
}
