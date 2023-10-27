package com.banana.banana_invoices.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(basePackages = "com.banana.banana_invoices")
//@Import({ReposConfig.class, ServicesConfig.class})
public class SpringConfig {


}
