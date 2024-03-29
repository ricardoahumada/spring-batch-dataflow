package com.banana.banana_invoices.batch.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

import java.text.SimpleDateFormat;
import java.util.Calendar;

//@Configuration
public class ScheduleConfig {
    @Autowired
    JobLauncher jobLauncher;

    // Inject a job
    @Autowired
    @Qualifier("myJob")
    Job job;
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");

    @Scheduled(fixedDelay = 5000, initialDelay = 5000)
    public void testLaunchsccheduledJob() throws Exception {

        try {

            System.out.println("Batch job scheduled starting");
            JobParameters jobParameters = new JobParametersBuilder().addString("time", format.format(Calendar.getInstance().getTime())).toJobParameters();
            JobExecution execution = jobLauncher.run(job, jobParameters);
            System.out.println("Exit Status : " + execution.getStatus());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
