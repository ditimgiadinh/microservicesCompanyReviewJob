package com.antn.jobms.job.mapper;

import com.antn.jobms.job.Job;
import com.antn.jobms.job.dto.JobWithCompanyDTO;
import com.antn.jobms.job.external.Company;

public class JobMapper {
    public static JobWithCompanyDTO mapToJobWithCompanyDto(
                    Job job,
            Company company) {
        JobWithCompanyDTO jobWithCompanyDTO = new JobWithCompanyDTO();
        jobWithCompanyDTO.setId(job.getId());
        jobWithCompanyDTO.setTitle(job.getTitle());
        jobWithCompanyDTO.setDescription(job.getDescription());
        jobWithCompanyDTO.setLocation(job.getLocation());
        jobWithCompanyDTO.setMaxSalary(job.getMaxSalary());
        jobWithCompanyDTO.setMinsalary(job.getMinsalary());
        jobWithCompanyDTO.setCompany(company);

        return jobWithCompanyDTO;
    }
}
