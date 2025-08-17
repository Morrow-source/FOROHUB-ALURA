package com.example.forohub.service;

import com.example.forohub.dto.DatosRegistroTopico;
import com.example.forohub.dto.DatosRespuestaTopico;
import com.example.forohub.model.Curso;
import com.example.forohub.model.Topico;
import com.example.forohub.model.Usuario;
import com.example.forohub.repository.CursoRepository;
import com.example.forohub.repository.TopicoRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TopicoService {

    @Autowired
    private TopicoRepository topicoRepository;
    @Autowired
    private CursoRepository cursoRepository;

    public DatosRespuestaTopico registrarTopico(DatosRegistroTopico datos, Usuario autor) {
        // Validación de duplicados
        if (topicoRepository.findByTituloAndMensaje(datos.titulo(), datos.mensaje()).isPresent()) {
            throw new ValidationException("Ya existe un tópico con el mismo título y mensaje.");
        }

        // Validación de existencia del curso
        Curso curso = cursoRepository.findById(datos.idCurso())
                .orElseThrow(() -> new EntityNotFoundException("No se encontró el curso con el ID proporcionado."));

        // Crear el nuevo Tópico
        Topico topico = new Topico();
        topico.setTitulo(datos.titulo());
        topico.setMensaje(datos.mensaje());
        topico.setAutor(autor);
        topico.setCurso(curso);

        // Guardar en la base de datos
        topicoRepository.save(topico);

        // Devolver el DTO de respuesta
        return new DatosRespuestaTopico(topico);
    }
}