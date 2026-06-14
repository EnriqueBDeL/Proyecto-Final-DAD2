<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="edu.ucam.domain.Usuario"%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="UTF-8">
<title>Control de Usuarios</title>
</head>
<body>

<h1>Gestión de Usuarios</h1>

<h2>Añadir usuario</h2>
<form action="ControlUsuarios" method="POST">
    <input type="hidden" name="ACTION_ID" value="INSERTAR_USUARIO">
    Usuario: <input type="text" name="username" required>
    Contraseña: <input type="password" name="password" required>
    Rol:
    <select name="rol">
        <option value="USER">USER</option>
        <option value="ADMIN">ADMIN</option>
    </select>
    <input type="submit" value="Guardar">
</form>

<br><hr><br>

<h2>Lista de usuarios</h2>

<%
    @SuppressWarnings("unchecked")
    List<Usuario> usuarios = (List<Usuario>) request.getAttribute("USUARIOS");

    if (usuarios != null && !usuarios.isEmpty()) {
%>
    <table border="1">
        <tr>
            <th>ID</th>
            <th>Usuario</th>
            <th>Nueva contraseña</th>
            <th>Rol</th>
            <th>Acciones</th>
        </tr>
<%
        for (Usuario u : usuarios) {
%>
        <tr>
            <form action="ControlUsuarios" method="POST">
                <td>
                    <%= u.getIdUsuario() %>
                    <input type="hidden" name="ACTION_ID" value="EDITAR_USUARIO">
                    <input type="hidden" name="id" value="<%= u.getIdUsuario() %>">
                </td>
                <td><input type="text" name="username" value="<%= u.getUsername() %>" required></td>
                <td><input type="password" name="password" placeholder="Mantener actual"></td>
                <td>
                    <select name="rol">
                        <option value="USER" <%= "USER".equals(u.getRol()) ? "selected" : "" %>>USER</option>
                        <option value="ADMIN" <%= "ADMIN".equals(u.getRol()) ? "selected" : "" %>>ADMIN</option>
                    </select>
                </td>
                <td>
                    <input type="submit" value="Editar">
                    <a href="ControlUsuarios?ACTION_ID=BORRAR_USUARIO&id=<%= u.getIdUsuario() %>">[Borrar]</a>
                </td>
            </form>
        </tr>
<%
        }
%>
    </table>
<%
    } else {
        out.print("<p><b>No hay usuarios registrados.</b></p>");
    }
%>

<br><br>
<a href="menuAdmin.jsp">Volver al Menú Administrador</a>
	
</body>
</html>
