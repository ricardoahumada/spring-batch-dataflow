package com.banana.batchdemo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.assertj.core.api.Assertions.assertThat;

//@SpringBootTest
@ExtendWith(OutputCaptureExtension.class)
class BatchDemoApplicationTests {
    @Test
    public void testBatchJobApp(CapturedOutput capturedOutput) throws Exception {
        final String JOB_RUN_MESSAGE = " was run";
        final String CREATE_TASK_MESSAGE = "Creating: TaskExecution{executionId=";
        final String UPDATE_TASK_MESSAGE = "Updating: TaskExecution with executionId=";
        final String JOB_ASSOCIATION_MESSAGE = "The job execution id ";
        final String EXIT_CODE_MESSAGE = "with the following {exitCode=0";

        SpringApplication.run(BatchDemoApplication.class);

        String output = capturedOutput.toString();
        assertThat(output).contains(JOB_RUN_MESSAGE);
        assertThat(output).contains(CREATE_TASK_MESSAGE);
        assertThat(output).contains(UPDATE_TASK_MESSAGE);
        assertThat(output).contains(EXIT_CODE_MESSAGE);

        int i = output.indexOf(JOB_ASSOCIATION_MESSAGE);

        assertThat(i).isGreaterThan(0);

        String taskTitle = "Demo Batch";
        Pattern pattern = Pattern.compile(taskTitle);
        Matcher matcher = pattern.matcher(output);

        int count = 0;
        while (matcher.find()) {
            count++;
        }
        assertThat(count).isEqualTo(1);
    }
}
