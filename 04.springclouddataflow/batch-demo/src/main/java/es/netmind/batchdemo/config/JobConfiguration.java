package es.netmind.batchdemo.config;

import es.netmind.batchdemo.BatchDemoProperties;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@EnableBatchProcessing
@EnableConfigurationProperties(BatchDemoProperties.class)
public class JobConfiguration {

    private static final Log logger = LogFactory.getLog(JobConfiguration.class);

    @Autowired
    private JobBuilderFactory jobs;

    @Autowired
    private StepBuilderFactory steps;

    @Bean
    public ItemReader<String> itemReader() {
        class StringReader implements ItemReader<String> {
            @Autowired
            private BatchDemoProperties config;
            String[] tokens = {"Hello", "world", "!", "How", "are", "you", "?"};
            int counter = 0;

            @Override
            public String read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
                if (counter < tokens.length - 1) {
                    System.out.println("StringReader: read with property: " + config.getMessage());
                    return tokens[counter++];
                }
                return null;
            }
        }

        return new StringReader();
    }

    @Bean
    public ItemProcessor<String, String> itemProcessor() {
        class StringProcessor implements ItemProcessor<String, String> {
            @Override
            public String process(String s) throws Exception {
                return s.toUpperCase();
            }
        }
        return new StringProcessor();
    }

    @Bean
    public ItemWriter<String> itemWriter() {
        class StringWriter implements ItemWriter<String> {

            @Override
            public void write(List<? extends String> list) throws Exception {
                System.out.println("Writing items:" + list);
            }
        }

        return new StringWriter();
    }

    @Bean
    public Step step1() {
        return steps.get("step1")
                .<String, String>chunk(2)
                .reader(itemReader())
                .processor(itemProcessor())
                .writer(itemWriter())
                .build();
    }

    @Bean
    public Job job1() {
        return jobs.get("job1")
                .start(step1())
                .build();
    }
}