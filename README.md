# API EduTech

API REST para la plataforma educativa EduTech desarrollada con Spring Boot.

## Endpoints Disponibles

### Gestión de Usuarios (`/api/usuarios`)

#### Registro y Autenticación
- `POST /api/usuarios/registro` - Registro de nuevo usuario
- `POST /api/usuarios/login` - Login unificado para todos los tipos de usuarios
- `POST /api/administrador/login` - Login específico para administradores

#### Operaciones CRUD
- `GET /api/usuarios` - Listar todos los usuarios
- `GET /api/usuarios/{id}` - Obtener usuario por ID
- `PUT /api/usuarios/{id}` - Actualizar usuario
- `PUT /api/usuarios/{id}/deshabilitar` - Deshabilitar usuario
- `DELETE /api/usuarios/{id}` - Eliminar usuario
- `PUT /api/usuarios/{id}/perfil` - Actualizar perfil (nombre y correo)

### Gestión de Cursos (`/api/cursos`)

- `POST /api/cursos` - Crear nuevo curso
- `GET /api/cursos` - Listar todos los cursos
- `GET /api/cursos/{sigla}` - Obtener curso por sigla
- `PUT /api/cursos/{sigla}` - Actualizar curso
- `DELETE /api/cursos/{sigla}` - Eliminar curso

### Gestión de Reseñas (`/api/resenas`)

- `POST /api/resenas` - Crear nueva reseña
- `GET /api/resenas` - Listar todas las reseñas
- `DELETE /api/resenas/{id}` - Eliminar reseña

### Gestión de Instructores (`/instructores`)

- `POST /instructores` - Crear nuevo instructor
- `GET /instructores` - Listar todos los instructores
- `GET /instructores/{id}` - Obtener instructor por ID
- `PUT /instructores/{id}` - Actualizar instructor
- `POST /instructores/{id}/evaluaciones` - Crear evaluación
- `GET /instructores/{id}/evaluaciones` - Ver evaluaciones del instructor
- `PUT /instructores/cursos/{cursoId}/contenido/{contenidoId}` - Actualizar contenido
- `POST /instructores/cursos/{cursoId}/evaluaciones` - Crear evaluación para curso
- `PUT /instructores/cursos/{cursoId}/evaluaciones/{evaluacionId}` - Actualizar evaluación

### Gestión de Gerente de Cursos (`/api/gerente-cursos`)

- `POST /api/gerente-cursos/cursos` - Crear curso
- `PUT /api/gerente-cursos/cursos/{id}` - Actualizar curso
- `DELETE /api/gerente-cursos/cursos/{id}` - Eliminar curso
- `POST /api/gerente-cursos/cursos/{cursoId}/instructores/{instructorId}` - Asignar instructor
- `GET /api/gerente-cursos/cursos/{cursoId}/reporte-inscripciones` - Generar reporte de inscripciones
- `GET /api/gerente-cursos/cursos/{cursoId}/reporte-rendimiento` - Generar reporte de rendimiento
- `POST /api/gerente-cursos/cursos/{cursoId}/aprobar` - Aprobar contenido
- `POST /api/gerente-cursos/cursos/{cursoId}/rechazar` - Rechazar contenido

### Gestión de Incidencias (`/incidencias`)

- `POST /incidencias` - Crear nueva incidencia
- `GET /incidencias` - Listar todas las incidencias
- `PUT /incidencias/{id}/respuesta` - Responder a una incidencia

### Gestión de Soporte (`/soportes`)

- `POST /soportes` - Crear nuevo soporte
- `GET /soportes` - Listar todos los soportes

### Gestión de Alumnos (`/usuarios`)

- `POST /usuarios` - Crear nuevo alumno
- `GET /usuarios` - Listar todos los alumnos
- `PUT /usuarios/{id}/deshabilitar` - Deshabilitar alumno

## Tecnologías Utilizadas

- Java 17
- Spring Boot 3.4.5
- Spring Data JPA
- MySQL
- SpringDoc OpenAPI (Swagger)

## Requisitos

- Java 17 o superior
- MySQL
- Maven

## Instalación

1. Clonar el repositorio
2. Configurar la base de datos MySQL
3. Acceder a la documentación Swagger en `http://localhost:8080/swagger-ui.html`
