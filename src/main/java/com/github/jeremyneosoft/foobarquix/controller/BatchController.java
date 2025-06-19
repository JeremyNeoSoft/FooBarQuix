package com.github.jeremyneosoft.foobarquix.controller;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BatchController {

	private final JobLauncher jobLauncher;
	
	private final Job foobarQuixJob;

	public BatchController(JobLauncher jobLauncher, Job foobarQuixJob) {
		this.jobLauncher = jobLauncher;
		this.foobarQuixJob = foobarQuixJob;
	}
	
	@GetMapping("/runBatch")
    public String runBatch(@RequestParam String inputFilePath, 
    		@RequestParam(required = false) String outputFilePath,
    		@RequestParam(required = false, defaultValue = "false") boolean alternate) throws Exception {
        JobParameters jobParameters = new JobParametersBuilder()
        		.addString("inputFilePath", inputFilePath)
                .addString("outputFilePath", outputFilePath)
                .addString("alternate", String.valueOf(alternate))
                .addLong("time", System.currentTimeMillis())
                .toJobParameters();
        jobLauncher.run(foobarQuixJob, jobParameters);
        return "Batch job has been launched";
    }
	
}
