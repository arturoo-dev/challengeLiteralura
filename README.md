# Challenge Literalura - Backend Java 📚

## Descripción
Literalura es un buscador de libros que se basa en la API de [Gutendex](https://gutendex.com/) para luego almacenar los datos de Libros en una base de datos Postgres.

## Características

`Opciones del menú`:

- `Buscar libro por título` - Se conecta a la api para obtener un resultado que es almacenado en la base de datos.
- `Listar libros registrados` - Muestra todos los libros registrados en la base de datos.
- `Listar autores registrados` - Muestra informacion de los autores registrados.
- `Listar autores vivos en un determinado año` - Muestra los autores vivos dada un año.
- `Listar libros por idioma` - Muestra los libros registrados por un idioma seleccionado.

Adicionalmente, la aplicación cuenta con validaciones en toda la aplicación tales como:
- Si un libro ya se encuentra registrado en la base de datos, no lo permite agregar.
- Si un usuario ingresa un carácter que no corresponde a las opciones, la aplicación da alerta que debe escoger las opciones correctas.

## Tecnologías utilizadas:
- Java 17
- Spring Boot
- Spring Data JPA
- PostgreSQL
- Maven

## Configuración
Crear las siguientes variables de entorno:

- `DB_HOST` - Esta contendrá el host de postgres.
- `DB_PORT` - Variable que contiene el puerto de conexión de postgres.
- `DB_NAME` - Variable que contiene el nombre de la base de datos.
- `DB_USER` - Variable que contiene el nombre de usuario de postgres.
- `DB_PASSWORD` - Variable que contiene la contraseña de conexión.

## Capturas

### Menú principal
![Captura de pantalla 2024-05-23 a la(s) 22 38 14](https://github.com/arturoodev/challengeLiteralura/assets/68787405/ea016cf1-2fe8-4314-b7d4-cf6582b07135)

### Listar Libros registrados.
![Captura de pantalla 2024-05-23 a la(s) 22 42 38](https://github.com/arturoodev/challengeLiteralura/assets/68787405/0e17778c-2092-448a-9b84-75ca3648088c)

### Listar Autores registrados.
![Captura de pantalla 2024-05-23 a la(s) 22 44 07](https://github.com/arturoodev/challengeLiteralura/assets/68787405/f9fcecc2-1ff4-4d59-99ec-4de6b32adca0)



