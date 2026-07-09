package com.antn.jobms.job;

import com.antn.jobms.job.dto.JobDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/jobs")
public class JobController {
    private JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    private List<Job> jobs = new ArrayList<>();

    @GetMapping
    public ResponseEntity<List<JobDTO>> findAll(){
        //return jobs;
        return ResponseEntity.ok(jobService.findAll());
        //return jobService.findAll();
    }

    @GetMapping("/{id}")
    //@GetMapping("/jobs/{id}")
    public ResponseEntity<JobDTO> getJobById(@PathVariable Long id){

        //Job job = jobService.getJobById(id);
        //if(job != null)
            //return new  ResponseEntity<>(job, HttpStatus.OK);
        //return new  ResponseEntity<>( HttpStatus.NOT_FOUND);
        JobDTO jobDTO = jobService.getJobById(id);
        if(jobDTO != null)
            return new  ResponseEntity<>(jobDTO, HttpStatus.OK);
        return new  ResponseEntity<>( HttpStatus.NOT_FOUND);
        //return new Job(1L, "TestJob", "TestJob", "2000", "2000", "loc");
    }

    //@PostMapping("/jobs")
    @PostMapping
    public ResponseEntity<String> createJob(@RequestBody Job job){
        //jobs.add(job);
        jobService.createJob(job);
        return new ResponseEntity<>("Job added sucesss",HttpStatus.CREATED);
        //return "Job added sucesss";
    }

    //@DeleteMapping("/jobs/{id}")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteJob(@PathVariable Long id) {
        boolean deleted = jobService.deleteJobById(id);
        if (deleted)
            return new ResponseEntity<>("Job deleted successfully", HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    //@PutMapping("/jobs/{id}")
    //@RequestMapping(value = "/job/{id}",method = RequestMethod.PUT)
    public ResponseEntity<String> updateJob(@PathVariable Long id,
                                            @RequestBody Job updatedJob) {
        boolean updated = jobService.updateJob(id, updatedJob);
        if (updated)
            return new ResponseEntity<> ("Job updated successfully", HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
