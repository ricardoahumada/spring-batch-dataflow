package com.banana.taskdemo;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties
public class TaskDemoProperties {
    private String message = "Hello world!!";
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }

}
