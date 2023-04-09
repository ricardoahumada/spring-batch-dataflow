package es.netmind.batchdemo;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties
public class BatchDemoProperties {
    private String message = "A message";
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }

}
