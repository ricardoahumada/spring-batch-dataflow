package es.netmind.recibosprocessor.model;

import com.opencsv.bean.CsvDate;
import lombok.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
//@AllArgsConstructor
@ToString
public class Recibo {
    private Long id;
    private Long propietario;
    @CsvDate(value = "yyyy-MM-dd")
    private LocalDate fecha_emision;
    @CsvDate(value = "yyyy-MM-dd")
    private LocalDate fecha_vencimiento;
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

    public Recibo(Long id, @NonNull LocalDate fecha_vencimiento, @NonNull String nombre_producto, @NonNull int cantidad, @NonNull float precio_unitario, @NonNull double base_imponible, @NonNull float impuestos, @NonNull double total) {
        this.id = id;
        this.fecha_vencimiento = fecha_vencimiento;
        this.nombre_producto = nombre_producto;
        this.cantidad = cantidad;
        this.precio_unitario = precio_unitario;
        this.base_imponible = base_imponible;
        this.impuestos = impuestos;
        this.total = total;
    }

    public Recibo(Recibo recibo) {
        this.id = recibo.id;
        this.propietario = recibo.propietario;
        this.fecha_emision = recibo.fecha_emision;
        this.fecha_vencimiento = recibo.fecha_vencimiento;
        this.nombre_contacto = recibo.nombre_contacto;
        this.direccion_contacto = recibo.direccion_contacto;
        this.direccion_envio = recibo.direccion_envio;
        this.nombre_producto = recibo.nombre_producto;
        this.cantidad = recibo.cantidad;
        this.precio_unitario = recibo.precio_unitario;
        this.base_imponible = recibo.base_imponible;
        this.impuestos = recibo.impuestos;
        this.total = recibo.total;
        this.estado = recibo.estado;
        this.valido = recibo.valido;
    }

    public double calcular_total() {
        return Math.round((base_imponible + (base_imponible * impuestos / 100)) * 100.0) / 100.0;
    }

    public double calcular_base_imponible() {
        return Math.round(cantidad * precio_unitario * 100.0) / 100.0;
    }

    public Set<String> esValido() {
        Set errores = new HashSet();

        if (!(propietario != null && propietario > 0L)) errores.add("propietario:" + propietario);
        if (fecha_vencimiento == null) errores.add("fecha_vencimiento:" + fecha_vencimiento);
        if (!(nombre_producto != null && !nombre_producto.trim().equals("")))
            errores.add("nombre_producto:" + nombre_producto);
        if (cantidad <= 0) errores.add("cantidad:" + cantidad);
        if (precio_unitario <= 0) errores.add("precio_unitario:" + precio_unitario);
        if (base_imponible <= 0 || (base_imponible != calcular_base_imponible()))
            errores.add("base_imponible:" + base_imponible + "<>" + calcular_base_imponible());
        if (impuestos < 0) errores.add("impuestos:" + impuestos);
        if (total != calcular_total()) errores.add("total:" + total + "<>" + calcular_total());

        return errores;
    }
}
