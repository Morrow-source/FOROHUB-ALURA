package com.example.forohub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Clase principal que inicia la aplicación Spring Boot.
 * Su única responsabilidad es arrancar el contexto de Spring.
 * No se debe añadir lógica de negocio aquí.
 */
@SpringBootApplication
public class ForohubApplication {

    public static void main(String[] args) {
        // Esta línea es la que inicia toda la magia de Spring Boot
        SpringApplication.run(ForohubApplication.class, args);
    }

}
