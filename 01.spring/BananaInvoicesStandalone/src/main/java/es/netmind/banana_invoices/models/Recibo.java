package es.netmind.banana_invoices.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Recibo {
    private Long propietario;
    private Date fecha_emision;
    private Date fecha_vencimiento;
    private String nombre_contacto;
    private String direccion_contacto;
    private String direccion_envio;
    private String nombre_producto;
    private int cantidad;
    private float precio_unitario;
    private double base_imponible;
    private float impuestos;
    private double total;
    private boolean estado;
    private boolean valido;

}
