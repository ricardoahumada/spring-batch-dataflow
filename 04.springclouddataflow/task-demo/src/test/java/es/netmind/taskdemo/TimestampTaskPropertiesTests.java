package es.netmind.taskdemo;

import org.junit.jupiter.api.Test;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class TimestampTaskPropertiesTests {

    @Test
    public void testEmptyFormat() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        TestPropertyValues testPropertyValues = TestPropertyValues.of("format:");
        testPropertyValues.applyTo(context);
        context.register(Conf.class);
        context.refresh();
        TimestampTaskProperties properties = context.getBean(TimestampTaskProperties.class);
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> {
            properties.getFormat();
        });
    }

    @Test
    public void testFormatDefault() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(Conf.class);
        context.refresh();
        TimestampTaskProperties properties = context.getBean(TimestampTaskProperties.class);
        assertThat(properties.getFormat()).as("result does not match default format.")
                .isEqualTo("yyyy-MM-dd HH:mm:ss.SSS");
    }

    @Test
    public void testFormatSet() {
        final String FORMAT = "yyyy";
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(Conf.class);
        context.refresh();
        TimestampTaskProperties properties = context.getBean(TimestampTaskProperties.class);
        properties.setFormat(FORMAT);
        assertThat(properties.getFormat()).as("result does not match established format.").isEqualTo(FORMAT);
    }

    @Configuration(proxyBeanMethods = false)
    @EnableConfigurationProperties(TimestampTaskProperties.class)
    static class Conf {

    }
}
