/*
 * This code is sample code, provided as-is, and we make NO
 * warranties as to its correctness or suitability for any purpose.
 *
 * We hope that it's useful to you. Enjoy.
 * Copyright LearningPatterns Inc.
 */

package es.netmind.banana_invoices.batch.config;

import es.netmind.banana_invoices.batch.processor.ReciboPagadoProcessor;
import es.netmind.banana_invoices.batch.processor.SimpleProcessor;
import es.netmind.banana_invoices.batch.reader.SimpleReader;
import es.netmind.banana_invoices.batch.writer.SimpleWriter;
import es.netmind.banana_invoices.models.Recibo;
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

import java.util.List;

@Configuration
@EnableBatchProcessing
@SuppressWarnings({"rawtypes", "unchecked"})
public class AppMainConfig {

    @Value("${api.verification.url}")
    private String apiUrl;
    @Autowired
    private JobBuilderFactory jobs;

    @Autowired
    private StepBuilderFactory steps;

    @Bean
    ItemReader<String> simpleRead() {
        return new SimpleReader();
    }

    @Bean
    ItemWriter<String> simpleWrite() {
        return new SimpleWriter();
    }

    @Bean
    ItemProcessor<String, String> simpleProccesor() {
        return new SimpleProcessor();
    }

    @Bean
    public Step step1() {
        return steps.get("step1")
                .allowStartIfComplete(true)
                .<String, String>chunk(2)
                .reader(simpleRead())
                .processor(simpleProccesor())
                .writer(simpleWrite())
                .build();
    }

    @Autowired
    public SynchronizedItemStreamReader<Recibo> s3Reader;

    @Bean
    public ItemWriter<Recibo> recWriter() {
        class RecWriter implements ItemWriter<Recibo> {

            @Override
            public void write(List<? extends Recibo> list) throws Exception {
                System.out.println("RecWriter write()....:" + list.size());
                for (Recibo item : list) System.out.printf("\t ...writing: %s\n", item);
            }
        }

        return new RecWriter();
    }

    @Bean
    public ItemProcessor<Recibo,Recibo> getRecProcessor(){
        return new ReciboPagadoProcessor();
    }

    @Bean
    public Step step2() {
        return steps.get("step2")
                .allowStartIfComplete(true)
                .<Recibo, Recibo>chunk(20)
                .reader(s3Reader)
                .processor(getRecProcessor())
                .writer(recWriter())
                .build();
    }

    @Bean("myJob")
    public Job procesadorItems() {
        return jobs.get("job1")
                .start(step2())
                .build();
    }

}