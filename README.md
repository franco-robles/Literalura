# LiterAlura
Programador: **Franco Robles**
Estudiante en **AluraLatam+Oracle**
## Descripción
**LiterAlura** es una aplicación desarrollada como parte del proyecto de Alura Latam y Oracle. La aplicación permite buscar libros a través de una API, almacenar los datos en una base de datos PostgreSQL y realizar diversas consultas sobre los libros y autores.

## Requisitos
- **Java 23**
- **Spring Boot 3.3.5**
- **PostgreSQL**

## Instalación
1. Clonar el repositorio.
2. Configurar la base de datos PostgreSQL.
3. Ejecutar el proyecto con Spring Boot.

## Funcionalidades Principales
1. **Buscar Libro:** Permite buscar un libro por su título a través de una API y almacenarlo en la base de datos si no existe.
2. **Buscar Libros por Autor:** Permite buscar libros almacenados en la base de datos por el nombre del autor.
3. **Buscar Libros por Idioma:** Permite listar libros almacenados en la base de datos según su idioma.
4. **Listar Libros Buscados Previamente:** Muestra todos los libros almacenados en la base de datos.
5. **Ver Autores Guardados:** Lista todos los autores almacenados en la base de datos.
6. **Ver Autores Vivos en un Año Específico:** Muestra los autores que estaban vivos en un año específico.

## Estructura del Proyecto
### Clases Principales

#### `ConsumoAPI`
Se encarga de realizar las solicitudes HTTP para obtener datos desde una URL.

#### `ConvierteDatos`
Implementa la interfaz `IConvierteDatos` y convierte JSON a objetos Java usando `ObjectMapper`.

#### `LiteraluraApplication`
Clase principal que ejecuta la aplicación Spring Boot y llama al método `mostrarMenu`.

### Repositorios
#### `LibroRepository`
Define métodos para interactuar con la base de datos de libros, como verificar si un libro existe y buscar libros por idioma.

#### `EscritorRepository`
Define métodos para interactuar con la base de datos de escritores, como verificar si un escritor existe, buscar escritores por nombre e identificar autores vivos en un año específico.

### Entidades
#### `Libro`
Representa un libro en la base de datos con atributos como título, descripción, géneros, idiomas, derechos de autor, cantidad de descargas, y una lista de autores.

#### `Escritor`
Representa un escritor en la base de datos con atributos como nombre, año de nacimiento, año de fallecimiento y una lista de libros asociados.

### Data Transfer Objects (DTOs)
#### `JsonResponse`
Mapea los datos JSON de la respuesta de la API a objetos Java.

#### `DatosLibros`
Representa los datos de un libro obtenidos de la API, incluyendo título, descripción, géneros, idiomas, derechos de autor, cantidad de descargas y una lista de autores.

#### `DatosEscritor`
Representa los datos de un escritor obtenidos de la API, incluyendo nombre, año de nacimiento y año de fallecimiento.

### Clase `Principal`
Contiene la lógica del menú de la aplicación y las operaciones principales para interactuar con la API y la base de datos.

## Uso
1. Ejecutar la aplicación.
2. Seguir las opciones del menú para buscar y gestionar libros y autores.

## Contribuciones
Los comentario y criticas son bienvenidas.


## Licencia
Este proyecto está bajo la Licencia MIT. Consulta el archivo LICENSE para más detalles.
