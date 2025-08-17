package com.example.forohub.repository;

import com.example.forohub.model.Topico;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TopicoRepository extends JpaRepository<Topico, Long> {
    // Para validar que no haya tópicos duplicados
    Optional<Topico> findByTituloAndMensaje(String titulo, String mensaje);
}