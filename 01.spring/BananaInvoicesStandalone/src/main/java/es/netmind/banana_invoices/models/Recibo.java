package es.netmind.banana_invoices.models;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Getter @Setter
@NoArgsConstructor @RequiredArgsConstructor
@ToString
@Entity
@Table(name = "recibo")
public class Recibo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "propietario")
    private Propietario propietario;
    private LocalDate fecha_emision;
    @NonNull
    private LocalDate fecha_vencimiento;
    private String nombre_contacto;
    private String direccion_contacto;
    private String direccion_envio;
    @NonNull
    private String nombre_producto;
    @NonNull
    private int cantidad;
    @NonNull
    private float precio_unitario;
    @NonNull
    private double base_imponible;
    @NonNull
    private float impuestos;
    @NonNull
    private double total;
    @NonNull
    private boolean estado;
    @NonNull
    private boolean valido;

    public boolean esValido(){
        return false;
    }
}
