package es.netmind.taskdemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.task.configuration.EnableTask;
import org.springframework.context.annotation.Bean;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@EnableTask
@SpringBootApplication
public class TaskDemoApplication {
	private static final Logger logger = LoggerFactory.getLogger(TaskDemoApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(TaskDemoApplication.class, args);
	}

    @Bean
	public TaskDemo timeStampTask() {
		return new TaskDemo();
	}

	/**
	 * A commandline runner that prints a timestamp.
	 */
	public static class TaskDemo implements CommandLineRunner {

		@Autowired
		private TaskDemoProperties config;

		@Override
		public void run(String... strings) {
			logger.info("This is the message: {}",this.config.getMessage());
		}

	}

}
