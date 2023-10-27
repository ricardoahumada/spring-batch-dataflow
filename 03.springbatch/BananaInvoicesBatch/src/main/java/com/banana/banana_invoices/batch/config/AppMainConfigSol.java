/*
 * This code is sample code, provided as-is, and we make NO
 * warranties as to its correctness or suitability for any purpose.
 *
 * We hope that it's useful to you. Enjoy.
 * Copyright LearningPatterns Inc.
 */

package com.banana.banana_invoices.batch.config;

import com.banana.banana_invoices.batch.listener.ReportTasklet;
import com.banana.banana_invoices.batch.processor.ReciboPagadoProcessor;
import com.banana.banana_invoices.batch.processor.ReciboValidoProcessor;
import com.banana.banana_invoices.batch.processor.SimpleProcessor;
import com.banana.banana_invoices.batch.reader.SimpleReader;
import com.banana.banana_invoices.batch.writer.ReciboJPAWriter;
import com.banana.banana_invoices.batch.writer.ReciboSimpleWriter;
import com.banana.banana_invoices.batch.writer.SimpleWriter;
import com.banana.banana_invoices.models.Recibo;
import com.banana.banana_invoices.persistence.IReciboRepo;
import com.banana.banana_invoices.persistence.ReciboInvalidoRepository;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.partition.support.MultiResourcePartitioner;
import org.springframework.batch.core.partition.support.Partitioner;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.PassThroughLineMapper;
import org.springframework.batch.item.support.CompositeItemProcessor;
import org.springframework.batch.item.support.SynchronizedItemStreamReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Configuration
@EnableBatchProcessing
@SuppressWarnings({"rawtypes", "unchecked"})
public class AppMainConfigSol {
    @Autowired
    private JobBuilderFactory jobs;

    @Autowired
    private StepBuilderFactory steps;

    /* RECIBOS */
    @Autowired
    public SynchronizedItemStreamReader<Recibo> s3Reader;

    @Autowired
    private IReciboRepo reciboRepo;
    @Autowired
    private ReciboInvalidoRepository invalidoRepository;

    @Bean
    public ItemWriter<Object> jpaWriter() {
        ReciboJPAWriter jpaWriter = new ReciboJPAWriter();
        jpaWriter.setReciboRepo(reciboRepo);
        jpaWriter.setInvalidoRepository(invalidoRepository);
        return jpaWriter;
    }

    @Bean
    public ItemWriter<Object> recWriter() {
        return new ReciboSimpleWriter();
    }

    @Bean
    public ItemProcessor<Recibo, Object> getRecPagadoProcessor() {
        return new ReciboPagadoProcessor();
    }

    @Bean
    public ItemProcessor<Recibo, Object> getRecValidoProcessor() {
        return new ReciboValidoProcessor();
    }

    //    COMPOSITE PROCESSOR
    @Bean
    public ItemProcessor validPagadoProcessor() {
        List<ItemProcessor<Recibo, Object>> processors = new ArrayList<>();
        processors.add(getRecValidoProcessor());
        processors.add(getRecPagadoProcessor());

        CompositeItemProcessor<Recibo, Object> compositeProcessor = new CompositeItemProcessor<>();
        compositeProcessor.setDelegates(processors);

        return compositeProcessor;
    }

    //    TASK EXECUTORS
    @Value("#{${executor : {size: 2, max: 2, queue: 5}}}")
    private Map<String, Integer> executorMap;

    @Bean
    public TaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(executorMap.get("size"));
        taskExecutor.setMaxPoolSize(executorMap.get("max"));
        taskExecutor.setQueueCapacity(executorMap.get("queue"));
        return taskExecutor;
    }

    //    REPORTING
    @Autowired
    private JobExplorer jobExplorer;

    @Bean
    public ReportTasklet reportTasklet() {
        return new ReportTasklet(jobExplorer);
    }

    //    STEPS AND JOBS
    @Value("${chunk.size}")
    private Integer chunkSize;

    @Bean
    public Step step2() {
        return steps.get("step2")
                .allowStartIfComplete(true)
                .<Recibo, Object>chunk(chunkSize)
                .reader(s3Reader)
                .processor(validPagadoProcessor())
                .writer(jpaWriter())
                .taskExecutor(taskExecutor())
                .build();
    }

    @Bean
    public Step reportStep() {
        return steps.get("reportStep")
                .allowStartIfComplete(true)
                .tasklet(reportTasklet())
                .build();
    }

    @Bean("myBatchJob")
    public Job procesadorItems() {
        return jobs.get("job2")
                .start(step2())
                .on("*").to(reportStep()).end()
                .build();
    }

}