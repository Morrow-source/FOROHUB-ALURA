package com.example.forohub.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "Usuario")
@Data
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idUsuario") // Nombre en la BD
    private Long id; // Nombre estandarizado en Java

    @Column(name = "Nombre")
    private String nombre;

    @Column(name = "Correo electronico", unique = true)
    private String correoElectronico;

    @Column(name = "Contraseña")
    private String contrasena;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "usuario_perfil",
            joinColumns = @JoinColumn(name = "Usuario_id", referencedColumnName = "idUsuario"),
            inverseJoinColumns = @JoinColumn(name = "Perfil_id", referencedColumnName = "idPerfil")
    )
    private Set<Perfil> perfiles;

    // --- Implementación de UserDetails ---
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return perfiles.stream()
                .map(perfil -> new SimpleGrantedAuthority(perfil.getNombre()))
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() { return this.contrasena; }

    @Override
    public String getUsername() { return this.correoElectronico; }

    @Override
    public boolean isAccountNonExpired() { return true; }

    @Override
    public boolean isAccountNonLocked() { return true; }

    @Override
    public boolean isCredentialsNonExpired() { return true; }

    @Override
    public boolean isEnabled() { return true; }
}