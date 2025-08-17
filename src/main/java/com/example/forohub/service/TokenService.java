package com.example.forohub.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.forohub.model.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    // Inyecta el valor de la propiedad 'api.security.secret' desde application.properties
    @Value("${api.security.secret}")
    private String apiSecret;

    /**
     * Genera un token JWT para un usuario autenticado.
     * @param usuario El usuario para el cual se generará el token.
     * @return El token JWT como un String.
     */
    public String generarToken(Usuario usuario) {
        try {
            // Se utiliza el algoritmo HMAC256 con el secreto de la aplicación
            Algorithm algorithm = Algorithm.HMAC256(apiSecret);
            return JWT.create()
                    .withIssuer("Foro Hub") // Emisor del token
                    .withSubject(usuario.getCorreoElectronico()) // **CAMBIO CLAVE**: Usamos el correo como "subject" (identificador del usuario)
                    .withClaim("id", usuario.getId()) // Se puede añadir información extra (claims)
                    .withExpiresAt(generarFechaExpiracion()) // Define el tiempo de expiración
                    .sign(algorithm); // Firma el token
        } catch (JWTCreationException exception){
            throw new RuntimeException("Error al generar el token JWT", exception);
        }
    }

    /**
     * Valida un token JWT y obtiene el "subject" (identificador del usuario).
     * @param token El token JWT a validar.
     * @return El "subject" del token (en nuestro caso, el correo electrónico del usuario).
     */
    public String getSubject(String token) {
        if (token == null) {
            throw new IllegalArgumentException("El token no puede ser nulo.");
        }
        try {
            Algorithm algorithm = Algorithm.HMAC256(apiSecret);
            return JWT.require(algorithm)
                    .withIssuer("Foro Hub") // Valida que el emisor sea el correcto
                    .build()
                    .verify(token) // Verifica la firma y la expiración
                    .getSubject(); // Obtiene el subject
        } catch (JWTVerificationException exception) {
            // Este catch es genérico, podrías manejar excepciones más específicas
            // como TokenExpiredException si quisieras dar un mensaje diferente.
            throw new RuntimeException("Token JWT inválido o expirado!");
        }
    }

    /**
     * Genera la fecha de expiración para el token.
     * @return Un objeto Instant que representa el momento de la expiración.
     */
    private Instant generarFechaExpiracion() {
        // El token expirará 2 horas después de su creación
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-05:00"));
    }
}