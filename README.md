<div align="center">

# 🎓 SISTEMA DE GESTIÓN UNIVERSITARIA Web

### Trabajo Final - Desarrollo de Aplicaciones Distribuidas II

![Java](https://img.shields.io/badge/Java_EE-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Tomcat](https://img.shields.io/badge/Apache_Tomcat-F8DC75?style=for-the-badge&logo=apachetomcat&logoColor=black)
![MySQL](https://img.shields.io/badge/MySQL-4479A1?style=for-the-badge&logo=mysql&logoColor=white)
![MVC](https://img.shields.io/badge/Patrón-MVC-blue?style=for-the-badge)
![Status](https://img.shields.io/badge/Status-Finalizado-brightgreen?style=for-the-badge)
<br>

<p align="center">
  <b>Una solución robusta en J2EE para la administración académica distribuida.</b><br>
  Implementación basada en el patrón Modelo-Vista-Controlador (MVC) y securización de accesos.
</p>

</div>

---

## 📋 Descripción del Proyecto

Este proyecto consiste en el desarrollo de una aplicación web J2EE que implementa la funcionalidad de un sistema de gestión universitaria. El objetivo es permitir la administración centralizada e integral de titulaciones, asignaturas, alumnos y profesores a través de un portal web interactivo.

El sistema cuenta con un acceso seguro (login) y distingue entre usuarios normales y administradores, delimitando claramente las capacidades de cada uno dentro de la plataforma.

---

## ⚙️ Arquitectura y Despliegue

La aplicación está diseñada bajo el **patrón de diseño Modelo-Vista-Controlador (MVC)**, separando claramente la lógica de negocio, el acceso a datos y la interfaz gráfica. 

Para garantizar la portabilidad y correcta ejecución en los laboratorios de la universidad, el sistema requiere la siguiente infraestructura:

| Componente | Tecnología | Función Principal / Requisito |
| :---: | :---: | :--- |
| **Servidor** | Apache Tomcat | Servidor de aplicaciones web sobre el que se despliega el proyecto. |
| **BBDD** | MySQL (XAMPP) | Almacenamiento persistente. La BBDD debe llamarse dad2_vuestrodni. |
| **Conexión** | context.xml | Fichero en META-INF que centraliza la cadena de conexión. |
| **Script SQL** | dad2.sql | Ubicado en WEB-INF/. Crea la estructura de tablas y el admin por defecto. |

---

## 🚀 Funcionalidades

El sistema está dividido en distintas entregas incrementales. Las funcionalidades implementadas incluyen:

### 🔐 Seguridad y Usuarios
- [x] **Autenticación:** Sistema de login obligatorio y opción de autoregistro.
- [x] **Gestión de Accesos:** Zona privada exclusiva para el administrador (admin/admin).
- [x] **CRUD Usuarios:** Creación, lectura, actualización y borrado exclusivo para administradores.

### 🎓 Gestión Académica
- [x] **Titulaciones:** CRUD completo de todas las carreras de la universidad.
- [x] **Asignaturas:** CRUD de asignaturas (vinculadas a una única titulación) con definición de capacidad máxima.
- [x] **Profesores:** CRUD de docentes y asignación a asignaturas.

### 📝 Alumnado y Matrículas
- [x] **Alumnos:** CRUD de la base de estudiantes.
- [x] **Matriculación:** Alta y baja de alumnos en asignaturas respetando los límites de aforo máximo.
- [x] **Reportes:** Listado de alumnos por asignatura exportable en formato CSV.
- [x] **Dashboard (Admin):** Panel de estadísticas con totales y porcentajes de ocupación.

---

## 🛠️ Tecnologías y Herramientas

| Tecnología | Uso en el proyecto |
| :--- | :--- |
| **Java EE / J2EE** | Ecosistema base para la aplicación web distribuida. |
| **Servlets & JSP** | Implementación de Controladores y Vistas (MVC). |
| **MySQL / JDBC** | Base de datos relacional y conexión a la misma. |
| **Apache Tomcat** | Despliegue del contenedor de servlets. |

---

## 👥 Autores

<div align="center">

  <table>
    <tr>
      <td align="center">
        <a href="https://github.com/EnriqueBDeL">
          <img src="https://github.com/EnriqueBDeL.png" width="100px;" alt="Foto Enrique"/><br>
          <sub><b>EnriqueBDeL</b></sub>
        </a>
      </td>
      <td align="center">
        <a href="https://github.com/Agata-gp">
          <img src="https://github.com/Agata-gp.png" width="100px;" alt="Foto Agata"/><br>
          <sub><b>Agata-gp</b></sub>
        </a>
      </td>
    </tr>
  </table>

  <br>
  <i>[ Desarrollado para la asignatura Desarrollo de Aplicaciones Distribuidas II, Grado en Ingeniería Informática, UCAM ]</i>
  
</div>
