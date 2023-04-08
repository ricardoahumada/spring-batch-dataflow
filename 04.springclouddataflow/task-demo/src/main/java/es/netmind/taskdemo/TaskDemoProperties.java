package es.netmind.taskdemo;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@ConfigurationProperties
//@Component
public class TaskDemoProperties {
    private String message = "Hello world!!";
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }

}
