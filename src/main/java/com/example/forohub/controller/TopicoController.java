package com.example.forohub.controller;

import com.example.forohub.dto.DatosActualizarTopico;
import com.example.forohub.dto.DatosRegistroTopico;
import com.example.forohub.dto.DatosRespuestaTopico;
import com.example.forohub.model.Topico;
import com.example.forohub.model.Usuario;
import com.example.forohub.repository.TopicoRepository;
import com.example.forohub.service.TopicoService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoService topicoService;

    @Autowired
    private TopicoRepository topicoRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<DatosRespuestaTopico> registrarTopico(@RequestBody @Valid DatosRegistroTopico datos,
                                                                UriComponentsBuilder uriBuilder,
                                                                Authentication authentication) {
        Usuario autor = (Usuario) authentication.getPrincipal();
        DatosRespuestaTopico respuesta = topicoService.registrarTopico(datos, autor);
        URI url = uriBuilder.path("/topicos/{id}").buildAndExpand(respuesta.id()).toUri();
        return ResponseEntity.created(url).body(respuesta);
    }

    @GetMapping
    public ResponseEntity<Page<DatosRespuestaTopico>> listarTopicos(@PageableDefault(size = 10, sort = "fechaCreacion") Pageable paginacion) {
        Page<DatosRespuestaTopico> paginaDeTopicos = topicoRepository.findAll(paginacion).map(DatosRespuestaTopico::new);
        return ResponseEntity.ok(paginaDeTopicos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosRespuestaTopico> detalleTopico(@PathVariable Long id) {
        Topico topico = topicoRepository.getReferenceById(id);
        return ResponseEntity.ok(new DatosRespuestaTopico(topico));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DatosRespuestaTopico> actualizarTopico(@PathVariable Long id, @RequestBody DatosActualizarTopico datos) {
        Topico topico = topicoRepository.getReferenceById(id);
        
        if (datos.titulo() != null && !datos.titulo().isBlank()) {
            topico.setTitulo(datos.titulo());
        }
        if (datos.mensaje() != null && !datos.mensaje().isBlank()) {
            topico.setMensaje(datos.mensaje());
        }
        
        return ResponseEntity.ok(new DatosRespuestaTopico(topico));
    }

    @SuppressWarnings("rawtypes")
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarTopico(@PathVariable Long id) {
        topicoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}