package es.netmind.banana_invoices.view;

import es.netmind.banana_invoices.services.IInventario;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;

public class GestorRecibos {

    @Autowired
    IInventario inventario;
    @Transactional
    public static void add_recibos() {

    }

    @Transactional
    public static void add_propietarios() {

    }

    @Transactional
    public static void asocia_recibo_propietario() {

    }

    public static void recibo_es_valido() {

    }

    public static void recibo_esta_pagado() {

    }

}
