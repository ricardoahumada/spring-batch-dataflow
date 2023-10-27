package com.banana.taskdemo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;

import static org.assertj.core.api.Assertions.assertThat;

//@SpringBootTest
@ExtendWith(OutputCaptureExtension.class)
class TaskDemoApplicationTests {

    @Test
    public void testTimeStampApp(CapturedOutput capturedOutput) throws Exception {
        final String CREATE_TASK_MESSAGE = "Creating: TaskExecution{executionId=";
        final String UPDATE_TASK_MESSAGE = "Updating: TaskExecution with executionId=";
        final String EXIT_CODE_MESSAGE = "with the following {exitCode=0";
        String[] args = {};

        SpringApplication.run(TaskDemoApplication.class, args);

        String output = capturedOutput.toString();
        assertThat(output.contains(CREATE_TASK_MESSAGE)).as("Test results do not show create task message: " + output)
                .isTrue();
        assertThat(output.contains(UPDATE_TASK_MESSAGE)).as("Test results do not show success message: " + output)
                .isTrue();
        assertThat(output.contains(EXIT_CODE_MESSAGE)).as("Test results have incorrect exit code: " + output).isTrue();

        String taskTitle = "Demo Task";
        Pattern pattern = Pattern.compile(taskTitle);
        Matcher matcher = pattern.matcher(output);
        int count = 0;
        while (matcher.find()) {
            count++;
        }
        assertThat(count).as("The number of task titles did not match expected: ").isEqualTo(1);
    }

}
