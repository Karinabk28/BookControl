# 📚 Book Control

**Book Control** es una aplicación web desarrollada con Spring Boot para gestionar y organizar lecturas. Permite consultar libros, crear listas personales y compartir valoraciones y reseñas.

## ✨ Funcionalidades

- Explorar y consultar libros.
- Organizar libros en listas personales.
- Consultar información y portadas de libros.
- Valorar libros y escribir reseñas.
- Gestionar los contenidos de la aplicación.

## 🛠️ Tecnologías utilizadas

- Java
- Spring Boot
- Spring Data JPA
- Hibernate
- MySQL
- Maven
- HTML, CSS y JavaScript

## 📋 Requisitos previos

Antes de instalar el proyecto, necesitas tener:

- Java instalado.
- Maven instalado.
- MySQL instalado y en ejecución.
- Git instalado.

## 🚀 Instalación y ejecución

### 1. Clonar el repositorio

Abre una terminal y ejecuta:

```bash
git clone https://github.com/Karinabk28/BookControl.git
```

Entra en la carpeta del proyecto:

```bash
cd BookControl
```

### 2. Crear la base de datos

Abre MySQL y ejecuta:

```sql
CREATE DATABASE bookcontrol;
```

### 3. Configurar la conexión a MySQL

Abre el archivo `src/main/resources/application.properties` y comprueba que la configuración sea correcta.

```properties
spring.application.name=bookcontrol

spring.datasource.url=jdbc:mysql://localhost:3306/bookcontrol?useSSL=false&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
```

**Importante:** si tu usuario de MySQL tiene contraseña, tendrás que escribirla en `spring.datasource.password`.

### 4. Ejecutar la aplicación

Desde la carpeta del proyecto, ejecuta:

```bash
mvn clean spring-boot:run
```

Cuando la aplicación se haya iniciado correctamente, abre el navegador en:

http://localhost:8080


## 👩‍💻 Autora

Proyecto desarrollado por **Karina**.

