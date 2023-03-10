package es.netmind.banana_invoices.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "propietario")
@Getter @Setter
public class Propietario {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long pid;
    @Column
    private String nombre;
    @Column
    private String email;
    @Column
    private String telefono;
    @Column
    private int seccion;
}
