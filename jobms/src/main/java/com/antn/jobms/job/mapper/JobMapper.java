package com.antn.jobms.job.mapper;

import com.antn.jobms.job.Job;
import com.antn.jobms.job.dto.JobDTO;
import com.antn.jobms.job.external.Company;
import com.antn.jobms.job.external.Review;

import java.util.List;

public class JobMapper {
    public static JobDTO mapToJobWithCompanyDto(
                    Job job,
            Company company,
                    List<Review> reviews) {
        JobDTO jobDTO = new JobDTO();
        jobDTO.setId(job.getId());
        jobDTO.setTitle(job.getTitle());
        jobDTO.setDescription(job.getDescription());
        jobDTO.setLocation(job.getLocation());
        jobDTO.setMaxSalary(job.getMaxSalary());
        jobDTO.setMinsalary(job.getMinsalary());
        jobDTO.setCompany(company);
        jobDTO.setReview(reviews);

        return jobDTO;
    }
}
