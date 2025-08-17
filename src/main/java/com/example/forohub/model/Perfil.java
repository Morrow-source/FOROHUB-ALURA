package com.example.forohub.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Perfil")
@Data
@NoArgsConstructor
public class Perfil {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idPerfil") // Nombre en la BD
    private Long id; // Nombre estandarizado en Java

    @Column(name = "Nombre", unique = true)
    private String nombre;
}