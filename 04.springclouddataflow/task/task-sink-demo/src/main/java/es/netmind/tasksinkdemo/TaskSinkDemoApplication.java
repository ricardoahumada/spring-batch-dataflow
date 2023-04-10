package es.netmind.tasksinkdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.task.launcher.annotation.EnableTaskLauncher;

@SpringBootApplication
@EnableTaskLauncher
public class TaskSinkDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaskSinkDemoApplication.class, args);
	}

}
