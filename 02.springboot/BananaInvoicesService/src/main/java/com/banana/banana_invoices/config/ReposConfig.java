package com.banana.banana_invoices.config;

import com.banana.banana_invoices.persistence.IPropietarioRepo;
import com.banana.banana_invoices.persistence.IReciboRepo;
import com.banana.banana_invoices.persistence.JPAIReciboRepo;
import com.banana.banana_invoices.persistence.JPAPropietarioRepo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ReposConfig {

    @Bean
    public IPropietarioRepo propRepository() {
        return new JPAPropietarioRepo();
    }

    @Bean
    public IReciboRepo recRepository() {
        return new JPAIReciboRepo();
    }
}
