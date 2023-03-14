package es.netmind.banana_invoices.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "propietario")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
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
