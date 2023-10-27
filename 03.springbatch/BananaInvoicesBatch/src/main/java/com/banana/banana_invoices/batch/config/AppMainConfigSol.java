/*
 * This code is sample code, provided as-is, and we make NO
 * warranties as to its correctness or suitability for any purpose.
 *
 * We hope that it's useful to you. Enjoy.
 * Copyright LearningPatterns Inc.
 */

package com.banana.banana_invoices.batch.config;

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
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.support.SynchronizedItemStreamReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
    public ItemProcessor<Recibo, Recibo> getRecPagadoProcessor() {
        return new ReciboPagadoProcessor();
    }

    @Bean
    public ItemProcessor<Recibo, Object> getRecValidoProcessor() {
        return new ReciboValidoProcessor();
    }

    @Bean
    public Step step2() {
        return steps.get("step2")
                .allowStartIfComplete(true)
                .<Recibo, Object>chunk(20)
                .reader(s3Reader)
                .processor(getRecValidoProcessor())
                .writer(jpaWriter())
                .build();
    }

    @Bean("myBatchJob")
    public Job procesadorItems() {
        return jobs.get("job1")
                .start(step2())
                .build();
    }

}