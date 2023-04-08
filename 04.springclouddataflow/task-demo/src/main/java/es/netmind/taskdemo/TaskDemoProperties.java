package es.netmind.taskdemo;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@ConfigurationProperties
@Component
public class TaskDemoProperties {
    public String getMessage() {
        return "Hello world!!";
    }

}
