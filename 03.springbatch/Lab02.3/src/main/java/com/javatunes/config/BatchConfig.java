/*
 * This code is sample code, provided as-is, and we make NO 
 * warranties as to its correctness or suitability for any purpose.
 * 
 * We hope that it's useful to you. Enjoy. 
 * Copyright LearningPatterns Inc.
 */

package com.javatunes.config;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.support.CompositeItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

import com.javatunes.batch.LoggingStepStartStopListener;
import com.javatunes.batch.MusicItemNameProcessor;
import com.javatunes.batch.MusicItemPriceProcessor;
import com.javatunes.batch.MusicItemReader;
import com.javatunes.batch.MusicItemWriter;
import com.javatunes.domain.MusicItem;

@Configuration
// Enable Spring batch processing
@EnableBatchProcessing
@SuppressWarnings({"rawtypes", "unchecked"}) // Needed because of obscure generics related warnings for readers/processors
public class BatchConfig {

	// @EnableBatchProcessing sets up the following two builders
    @Autowired
    private JobBuilderFactory jobs;

    @Autowired
    private StepBuilderFactory steps;
    
    @Bean
    @StepScope
    //@JobScope
    public ItemReader<MusicItem> getReader(@Value("#{jobParameters['time']}") String timeP){
    	System.out.printf("Time parameter en ItemReader: %s\n", timeP);
    	return new MusicItemReader();
    }
    
    @Bean
    public ItemWriter<MusicItem> getWriter(){
    	return new MusicItemWriter();
    }
    
    @Bean
    public ItemProcessor<MusicItem, MusicItem> nameProcessor(){
    	return new MusicItemNameProcessor();
    }
    
    @Bean
    public ItemProcessor<MusicItem, MusicItem> priceProcessor(){
    	return new MusicItemPriceProcessor();
    }
    
    @Bean
    public ItemProcessor compositeProcessor(){
    	//return new MusicItemNameProcessor();
    	List<ItemProcessor<MusicItem, MusicItem>> processors= new ArrayList<>();
    	processors.add(nameProcessor());
    	processors.add(priceProcessor());
    	
    	CompositeItemProcessor<MusicItem, MusicItem> compProcessor= new CompositeItemProcessor<>();
    	compProcessor.setDelegates(processors);
    	
    	return compProcessor;
    }
    
    private static final String WILL_BE_INJECTED = null;

    
    @Bean
    public Step step1() {
    	return steps.get("step1").<MusicItem, MusicItem>chunk(4)
    	.reader(getReader(WILL_BE_INJECTED))
    	//.processor(nameProcessor())
    	.processor(compositeProcessor())
    	.writer(getWriter())
    	.listener(new LoggingStepStartStopListener())
    	.build();
    }
    
    @Bean
    public Step step2() {
    	return steps.get("step2").<MusicItem, MusicItem>chunk(4)
    	.reader(getReader(WILL_BE_INJECTED))
    	.processor(priceProcessor())
    	.writer(getWriter())
    	.build();
    }
    
    @Bean("myJob")
    public Job procesadorItems() {
    	return jobs.get("job1")
    			.start(step1())
    			//.next(step2())
    			.build();
    }
    
    @Bean("myJob2")
    public Job procesadorItems2() {
    	return jobs.get("job2")
    			.start(step1())
    			//.next(step2())
    			.build();
    }
 
    /*@Autowired
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
            JobParameters jobParameters = new JobParametersBuilder()
            		.addString("time", format.format(Calendar.getInstance().getTime()))
            		.toJobParameters();
            JobExecution execution = jobLauncher.run(job, jobParameters);
            System.out.println("Exit Status : " + execution.getStatus());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/
    
}