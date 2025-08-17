package com.example.forohub.service;

import com.example.forohub.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AutenticacionService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // CORRECCIÓN: Spring Security llama a este método pasándole el "username" que
        // el usuario envió. En nuestro caso, es el correo electrónico.
        // Por lo tanto, llamamos al método corregido del repositorio.
        return usuarioRepository.findByCorreoElectronico(username);
    }
}