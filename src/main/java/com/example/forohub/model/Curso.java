package com.example.forohub.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Curso")
@Data
@NoArgsConstructor
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idCurso") // Nombre en la BD
    private Long id; // Nombre estandarizado en Java

    @Column(name = "Nombre")
    private String nombre;

    @Column(name = "Categoria")
    private String categoria;
}