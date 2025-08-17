FOROHUB API

API REST para el backend de un foro de discusión, desarrollado como parte del desafío de Alura Latam. La API se centra en la gestión de "Tópicos", permitiendo a los usuarios interactuar con ellos a través de un sistema CRUD completo y seguro.

✨ Características Principales

Gestión de Tópicos (CRUD):

✅ Crear un nuevo tópico.

✅ Listar todos los tópicos existentes con paginación.

✅ Mostrar los detalles de un tópico específico.

✅ Actualizar la información de un tópico.

✅ Eliminar un tópico de la base de datos.

Seguridad:

✅ Autenticación de usuarios basada en Tokens JWT (JSON Web Tokens).

✅ Endpoints protegidos que requieren un token de autorización válido.

Validaciones:

✅ Reglas de negocio aplicadas para evitar la duplicación de tópicos.

✅ Validación de los datos de entrada en los endpoints.

Persistencia:

✅ Uso de una base de datos relacional MySQL para la persistencia de los datos.

🛠️ Tecnologías Utilizadas

Lenguaje: Java 21

Framework: Spring Boot 3.5.4

Seguridad: Spring Security

Persistencia: Spring Data JPA (Hibernate)

Base de Datos: MySQL

Gestión de Dependencias: Apache Maven

Utilidades: Lombok

🚀 Empezando

Sigue estas instrucciones para configurar y ejecutar el proyecto en tu entorno local.

Prerrequisitos

Asegúrate de tener instalado lo siguiente:

- JDK 21 o superior.
- Apache Maven.
- Un servidor de MySQL.
- Un IDE de tu preferencia (IntelliJ IDEA, VS Code, Eclipse).
- Una herramienta para probar APIs como Postman.
- 
Instalación y Configuración

Clona el repositorio:

git clone https://github.com/tu-usuario/foro-hub.git
cd foro-hub

Crea la Base de Datos:
Abre tu cliente de MySQL y ejecuta el siguiente comando para crear la base de datos:

CREATE DATABASE forohub;

Sino, creala manualmente:
<img width="1027" height="531" alt="image" src="https://github.com/user-attachments/assets/079db380-0ec7-4ec9-a0ed-5b1ebff847a7" />

Configura las variables de entorno:
En el archivo src/main/resources/application.properties, actualiza las credenciales de tu base de datos:

# URL de la base de datos
spring.datasource.url=jdbc:mysql://localhost:(puerto)/forohub

# Usuario y contraseña de tu MySQL
spring.datasource.username=root
spring.datasource.password=TU_CONTRASEÑA_SECRETA

# Configuración de Hibernate
spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect

Ejecuta la aplicación:
Puedes ejecutar la aplicación desde tu IDE (buscando la clase ForohubApplication y ejecutándola) o usando Maven en la terminal:

./mvnw spring-boot:run

El servidor se iniciará en http://localhost:3000 por defecto (o el puerto que hayas configurado).

📖 Documentación de la API

La API expone los siguientes endpoints:

Autenticación
POST /login

Obtiene un token JWT para poder acceder a los endpoints protegidos.

Autenticación: Pública.

Request Body:
{
    "correoElectronico": "admin@forohub.com",
    "contrasena": "123456"
}

Respuesta Exitosa (200 OK):
{
    "jwtToken": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
}

Tópicos
GET /topicos: Lista todos los tópicos de forma paginada.

Autenticación: Requiere Bearer Token.

POST /topicos: Crea un nuevo tópico.

Autenticación: Requiere Bearer Token.

Request Body:
{
    "titulo": "Duda sobre la anotación @Entity",
    "mensaje": "¿Cuál es la diferencia entre usar @Entity y no usarla?",
    "idCurso": 1
}
GET /topicos/{id}

Muestra los detalles de un tópico específico.

Autenticación: Requiere Bearer Token.

PUT /topicos/{id}: Actualiza el título y/o el mensaje de un tópico existente.

Autenticación: Requiere Bearer Token.

Request Body:
{
    "titulo": "Título actualizado",
    "mensaje": "Mensaje actualizado."
}

DELETE /topicos/{id}: Elimina un tópico de la base de datos.

Autenticación: Requiere Bearer Token.

Respuesta Exitosa: 204 No Content

🧪 Cómo Probar con Postman

Paso 1: Obtener el Token de Autenticación

Realiza una petición POST a http://localhost:3000/login con las credenciales del usuario.

Copia el jwtToken de la respuesta.

Paso 2: Realizar una Petición Protegida

Crea una nueva petición (ej. GET http://localhost:3000/topicos).

Ve a la pestaña Authorization.

Selecciona "Bearer Token" en el menú "Type".

Pega el token copiado en el campo "Token".

Envía la petición. ¡Ahora deberías tener acceso!

📝 Estado del Proyecto

Proyecto finalizado y funcional.
