/*
 * This code is sample code, provided as-is, and we make NO 
 * warranties as to its correctness or suitability for any purpose.
 * 
 * We hope that it's useful to you. Enjoy. 
 * Copyright LearningPatterns Inc.
 */

package com.javatunes.batch;


import static org.junit.jupiter.api.Assertions.*;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;

import com.javatunes.config.SpringConfig;
	
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes={SpringConfig.class})
public class ItemStepTest {

	// Inject the JobLauncher
	@Autowired
	JobLauncher jobLauncher;
	
	// Inject a job
	@Autowired
	Job job;	
	
    @Test
    public void testLaunchJob() throws Exception {
     
    	try {
     
    		
    		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
    		JobParameters jobParameters = new JobParametersBuilder()
    				.addString("time", format.format(Calendar.getInstance().getTime()))
    				.addString("name", "Ricardo")
    				.toJobParameters();
    		
    		
    		JobExecution execution = jobLauncher.run(job, jobParameters);
    		System.out.println("Exit Status : " + execution.getStatus());
    		assertTrue(execution.getStatus() == BatchStatus.COMPLETED, "Exit status should be COMPLETED");
     
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    }

    
}
