package com.banana.banana_invoices.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ReposConfig.class, ServicesConfig.class})
@ComponentScan(basePackages = "com.banana.banana_invoices.batch.config.AppMainConfig")
public class SpringConfig {

}
