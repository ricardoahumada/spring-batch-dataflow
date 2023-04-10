package es.netmind.tasksinkdemo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class TaskSinkDemoApplicationTests {

	@Test
    public void whenSpringContextIsBootstrapped_thenNoExceptions() {
    }

}
