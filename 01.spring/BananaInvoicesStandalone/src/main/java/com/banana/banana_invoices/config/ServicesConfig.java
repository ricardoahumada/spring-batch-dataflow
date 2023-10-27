package com.banana.banana_invoices.config;

import com.banana.banana_invoices.persistence.IPropietarioRepo;
import com.banana.banana_invoices.persistence.IReciboRepo;
import com.banana.banana_invoices.services.IInventario;
import com.banana.banana_invoices.services.InventarioImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServicesConfig {

    @Autowired
    IPropietarioRepo propietariosRepo;

    @Autowired
    IReciboRepo recivossRepo;

    @Bean
    public IInventario getInventario(){
        InventarioImpl inventario = new InventarioImpl();
        inventario.setPropietariosRepo(propietariosRepo);
        inventario.setRecivosRepo(recivossRepo);
        return inventario;
    }

}
