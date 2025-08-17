package com.example.forohub.dto;

import com.example.forohub.model.StatusTopico;
import com.example.forohub.model.Topico;

import java.time.LocalDateTime;

public record DatosRespuestaTopico(
        Long id,
        String titulo,
        String mensaje,
        LocalDateTime fechaCreacion,
        StatusTopico status,
        String autor,
        String curso
) {
    // Constructor para mapear fácil desde la Entidad
    public DatosRespuestaTopico(Topico topico) {
        this(topico.getId(),
             topico.getTitulo(),
             topico.getMensaje(),
             topico.getFechaCreacion(),
             topico.getStatus(),
             topico.getAutor().getNombre(),
             topico.getCurso().getNombre());
    }
}
