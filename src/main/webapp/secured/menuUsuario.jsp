<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="edu.ucam.domain.Usuario" %>

<%
    Usuario usuarioLogado = (Usuario) session.getAttribute("usuario");
    String nombre = "";

    if (usuarioLogado != null) {
        nombre = usuarioLogado.getUsername();
    } else {
        nombre = "Usuario";
    }
%>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Panel Usuario</title>
</head>
<body>

    <h1>Panel del Usuario</h1>

    <p>Bienvenido, <strong><%= nombre %></strong></p>

    <p>¿Qué deseas gestionar?</p>
    <ul>
        <li><a href="ControlProfesores?ACTION_ID=LISTAR_PROFESORES">Gestión de Profesores</a></li>
        <li><a href="ControlTitulaciones?ACTION_ID=LISTAR_TITULACIONES">Gestión de Titulaciones</a></li>
        <li><a href="ControlAsignaturas?ACTION_ID=LISTAR_ASIGNATURAS">Gestión de Asignaturas</a></li>
    </ul>

    <br><br>
    <a href="${pageContext.request.contextPath}/LogoutServlet" style="color: red;">[ Cerrar Sesión ]</a>

</body>
</html>