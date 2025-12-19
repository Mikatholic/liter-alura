# LiterAlura - Catálogo de Libros 📚
LiterAlura es una aplicación de consola desarrollada en Java utilizando el framework Spring Boot. El objetivo del proyecto es interactuar con la API Gutendex para buscar libros, procesar datos en formato JSON y almacenarlos en una base de datos relacional para su posterior consulta y análisis estadístico.

## 🚀 Funcionalidades
1. Gestión de Libros

   - Búsqueda por Título: Obtiene datos directamente de Gutendex. Si el libro no existe en la base de datos local, lo registra automáticamente junto con su autor.

   - Listado Total: Visualización de todas las obras registradas con detalles de descargas e idioma.

   - Filtro por Idioma: Consulta de libros filtrados por siglas internacionales (es, en, fr, pt).

2. Gestión de Autores

   - Registro Automatizado: Al guardar un libro, el autor se procesa y vincula mediante relaciones JPA.

   - Consulta Cronológica: Listado de autores que estaban vivos en un año específico ingresado por el usuario.

   - Historial de Autores: Visualización completa de los escritores detectados en las búsquedas.

## 🛠️ Tecnologías Utilizadas
Java 21: Lenguaje de programación principal.

Spring Boot 3.x: Framework para la configuración y ejecución de la aplicación.

Spring Data JPA: Para el mapeo objeto-relacional (ORM) y la persistencia de datos.

PostgreSQL: Sistema de gestión de base de datos relacional.

Jackson: Biblioteca para el manejo y conversión de datos JSON a objetos Java.

HttpClient: Para realizar las solicitudes asíncronas a la API Gutendex.

## 📂 Estructura del Proyecto
El código se organiza de forma modular para facilitar su mantenimiento:

model: Contiene las Entidades (Libro, Autor) y los Records DTO (Datos, DatosLibro, DatosAutor) para el mapeo de la API.

repository: Interfaces que extienden JpaRepository para la comunicación con la base de datos.

service: Clases de lógica de negocio, incluyendo el consumo de la API y la conversión de datos.

principal: Clase que gestiona el menú interactivo y la entrada del usuario mediante Scanner.

## 📋 Requisitos Previos
Java JDK 21 o superior.

Maven.

Instancia de PostgreSQL activa.


## ⚙️ Cómo Ejecutar el Proyecto

### 1. Clonar el Repositorio:

```bash
  git clone https://github.com/Mikatholic/liter-alura

```

### 2 Base de Datos: 
Tener instalado PostgreSQL y crear una base llamada liter_alura.

### 3. Construir (Maven): 
Asegúrate de tener las dependencias de Spring Boot y Jackson configuradas en tu pom.xml.

### 4. Configura Properties

Configura tus credenciales de base de datos en el archivo: 
  src/main/resources/application.properties:

```bash
spring.datasource.url=jdbc:postgresql://localhost:5432/liter_alura
spring.datasource.username=tu_usuario
spring.datasource.password=tu_contraseña
spring.jpa.hibernate.ddl-auto=update
```

### 5. Ejecutar: 
Ejecuta la aplicación desde tu IDE o mediante la terminal con:

```bash
./mvnw spring-boot:run
```

### 6. Interacción: 
La aplicación se iniciará y te pedirá seleccionar una generación del menú de consola.

## 🖥️ Interfaz de Usuario (Consola)
Al iniciar la aplicación, se presenta un menú interactivo:

Buscar libro por título: Ingresa el nombre y la app hace el resto.

Listar libros registrados: Ver tu colección personal.

Listar autores registrados: Conoce a los escritores en tu base.

Listar autores vivos en un determinado año: Consulta histórica rápida.

Listar libros por idioma: Organiza tu biblioteca por lenguaje.
