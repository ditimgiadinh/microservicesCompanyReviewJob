package com.antn.jobms.job.impl;

/*
import com.embarkx.firstjobapp.job.Job;
import com.embarkx.firstjobapp.job.JobRepository;
import com.embarkx.firstjobapp.job.JobService;
*/
import com.antn.jobms.job.Job;
import com.antn.jobms.job.JobRepository;
import com.antn.jobms.job.JobService;
import com.antn.jobms.job.clients.CompanyClient;
import com.antn.jobms.job.clients.ReviewClient;
import com.antn.jobms.job.dto.JobDTO;
import com.antn.jobms.job.external.Company;
import com.antn.jobms.job.external.Review;
import com.antn.jobms.job.mapper.JobMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class JobServiceImpl implements JobService {


    //private  List<Job> jobs = new ArrayList<>();
    JobRepository jobRepository;
    private Long nextId = 1L;

    @Autowired
    RestTemplate restTemplate;

    private CompanyClient companyClient;
    private ReviewClient reviewClient;

    public JobServiceImpl(JobRepository jobRepository, CompanyClient companyClient, ReviewClient reviewClient) {

        this.jobRepository = jobRepository;
        this.companyClient = companyClient;
        this.reviewClient = reviewClient;
    }

    @Override
    public List<JobDTO> findAll() {

        List<Job> jobs = jobRepository.findAll();
        List<JobDTO> jobDTOS = new ArrayList<>();


        return jobs.stream().map(this::convertToDto).collect(Collectors.toList());
        //RestTemplate  restTemplate = new RestTemplate();
        //Company company = restTemplate.getForObject("http://localhost:8081/companies/1", Company.class);
        //System.out.println("COMPANY :" + company.getName());
        //System.out.println("COMPANY :" + company.getId());

        //return jobWithCompanyDTOs;
        //return jobRepository.findAll();
        //return List.of();
        //return jobs;
    }

    private JobDTO convertToDto(Job job)
    {
        //JobWithCompanyDTO jobWithCompanyDTO = new JobWithCompanyDTO();
        //jobWithCompanyDTO.setJob(job);
        //RestTemplate restTemplate = new RestTemplate();

//        Company company = restTemplate.getForObject(
//                "http://COMPANY-SERVICE:8081/companies/" + job.getCompanyId(),
//                Company.class);
        Company company =companyClient.getCompany(job.getCompanyId());
        List<Review> reviews = reviewClient.getReviews(job.getCompanyId());

//        ResponseEntity<List<Review>> reviewResponse = restTemplate.exchange(
//                "http://REVIEW-SERVICE:8083/reviews?companyId=" + job.getCompanyId(),
//                HttpMethod.GET,
//                null,
//                new ParameterizedTypeReference<List<Review>>() {
//                });

        //List<Review> reviews = reviewResponse.getBody();

        JobDTO jobDTO = JobMapper.mapToJobWithCompanyDto(job,company,reviews);
        //jobDTO.setCompany(company);
        //jobWithCompanyDTOs.add(jobWithCompanyDTO);
        return jobDTO;
    }

    @Override
    public void createJob(Job job) {
        //job.setId(nextId++);
        jobRepository.save(job);
        //jobs.add(job);
    }

    @Override
    public JobDTO getJobById(Long id) { //public Job getJobById(Long id) {
        /*
        for(Job job : jobs){
            if(job.getId().equals(id)){
                return job;
            }
        }
        return null;
        */
        //return jobRepository.findById(id).orElse(null);
        Job job =jobRepository.findById(id).orElse(null);
        return convertToDto(job);
    }

    @Override
    public boolean deleteJobById(Long id) {
        try {
            jobRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }

        /*
        Iterator<Job> iterator = jobs.iterator();
        while (iterator.hasNext()) {
            Job job = iterator.next();
            if (job.getId().equals(id)) {
                iterator.remove();
                return true;
            }
        }
        return false;
         */
    }

    @Override
    public boolean updateJob(Long id, Job updatedJob)
    {
        Optional<Job> jobOptional = jobRepository.findById(id);
        if (jobOptional.isPresent()) {
            Job job = jobOptional.get();
            job.setTitle(updatedJob.getTitle());
            job.setDescription(updatedJob.getDescription());
            job.setMinsalary(updatedJob.getMinsalary());
            job.setMaxSalary(updatedJob.getMaxSalary());
            job.setLocation(updatedJob.getLocation());
            jobRepository.save(job);
            return true;
        }
        return false;
         /*
        for (Job job : jobs) {
            if (job.getId().equals(id)) {
                job.setTitle(updatedJob.getTitle());
                job.setDescription(updatedJob.getDescription());
                job.setMinsalary(updatedJob.getMinsalary());
                job.setMaxSalary(updatedJob.getMaxSalary());
                job.setLocation(updatedJob.getLocation());
                return true;
            }
        }
        */
    }
}
