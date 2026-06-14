<%@ page import="edu.ucam.domain.Usuario" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
    // Recuperamos el usuario de la sesión
    Usuario usuarioLogado = (Usuario) session.getAttribute("usuario");
    String nombre = "";

    // Usamos el if-else clásico
    if (usuarioLogado != null) {
        nombre = usuarioLogado.getUsername();
    } else {
        nombre = "Administrador";
    }
%>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Panel Administrador</title>
</head>
<body>

    <h1>Panel del Administrador</h1>

    <p>Bienvenido, <strong><%= nombre %></strong></p>
    <p style="color: blue;"><i>Tienes privilegios de administrador.</i></p>

    <p>¿Qué deseas gestionar?</p>
    <ul>
        <li><a href="ControlUsuarios?ACTION_ID=LISTAR_USUARIOS"><b>Gestión de Usuarios (Exclusivo Admin)</b></a></li>
        
        <li><a href="../ControlProfesores?ACTION_ID=LISTAR_PROFESORES">Gestión de Profesores</a></li>
        <li><a href="../ControlTitulaciones?ACTION_ID=LISTAR_TITULACIONES">Gestión de Titulaciones</a></li>
        <li><a href="../ControlAsignaturas?ACTION_ID=LISTAR_ASIGNATURAS">Gestión de Asignaturas</a></li>
    </ul>

    <br><br>
    <a href="${pageContext.request.contextPath}/LogoutServlet" style="color: red;">[ Cerrar Sesión ]</a>

</body>
</html>