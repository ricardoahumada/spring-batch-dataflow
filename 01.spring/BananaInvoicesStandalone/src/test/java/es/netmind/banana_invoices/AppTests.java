package es.netmind.banana_invoices;

import es.netmind.banana_invoices.config.SpringConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes={SpringConfig.class})
class AppTests {

	@Test
	void contextLoads() {
	}

}
