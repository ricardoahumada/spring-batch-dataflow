package com.banana.banana_invoices.view;

import ch.qos.logback.classic.jul.JULHelper;
import com.banana.banana_invoices.config.SpringConfig;
import com.banana.banana_invoices.models.Propietario;
import com.banana.banana_invoices.models.Recibo;
import com.banana.banana_invoices.services.IInventario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.Enumeration;
import java.util.Set;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class GestorRecibos {
    private static IInventario inventario;

    public static void main(String[] args) {
        System.out.println("\nIniciando Gestor de recibos....\n");

        loadContext();

        System.out.println("Recibiendo datos....\n");
        Recibo reciboAAnadir = new Recibo(LocalDate.now(), "Cuaderno", 120, 0.9f, 180d, 15f, 207d, true, true);

        Propietario propietarioAAnadir = new Propietario("ana", "a@a.com", "1234567", 1);
        System.out.println("....\n");


        Long newRecId = add_recibo(reciboAAnadir);
        System.out.println("Recibo añadido: " + newRecId);

        Long newPropId = add_propietario(propietarioAAnadir);
        System.out.println("Propietario añadido: " + newRecId);

        boolean isOK = asocia_recibo_propietario(newRecId, newPropId);
        if (isOK) System.out.println("Propietario: " + newPropId + " asociado a recibo: " + newRecId);
        else System.out.println("No fue posible asociar propietario: " + newPropId + " a recibo: " + newRecId);

        boolean valid = recibo_es_valido(newRecId);
        if (valid) System.out.println("Recibo: " + newRecId + " es válido :))");
        else System.out.println("Recibo: " + newRecId + " NO es válido :((");

        boolean pagado = recibo_esta_pagado(newRecId);
        if (valid) System.out.println("Recibo: " + newRecId + " está pagado 🎉");
        else System.out.println("Recibo: " + newRecId + " NO está pagado :-/");
    }

    private static void loadContext() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);

        inventario = context.getBean(IInventario.class);
    }

    @Transactional
    private static Long add_recibo(Recibo rec) {
        inventario.saveRecibo(rec);
        return rec.getId();
    }

    @Transactional
    private static Long add_propietario(Propietario prop) {
        inventario.savePropietario(prop);
        return prop.getPid();
    }

    @Transactional
    private static boolean asocia_recibo_propietario(Long idrec, Long idprop) {
        inventario.asocia(idrec, idprop);
        return true;
    }

    private static boolean recibo_es_valido(Long idrec) {
        Set<String> errores = inventario.esValidoRecibo(idrec);
        if (errores.size() == 0) return true;
        else {
            System.out.println("⚠ Errores en recibo " + idrec + ":");
            for (String err : errores) System.out.println("\t"+err);
            return false;
        }
    }

    private static boolean recibo_esta_pagado(Long idrec) {
        return inventario.estaPagadoRecibo(idrec);
    }

}
