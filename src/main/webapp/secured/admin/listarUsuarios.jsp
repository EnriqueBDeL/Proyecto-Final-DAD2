<%@ page import="java.util.List" %>
<%@ page import="edu.ucam.domain.Usuario" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
    Usuario usuarioLogado = (Usuario) session.getAttribute("usuario");
    if (usuarioLogado == null) {
        response.sendRedirect("../../login.jsp");
        return;
    }
%>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Listar Usuarios</title>
    </head>
<body>

    <h1>Listado de Usuarios</h1>
    <div class="bienvenida">
        Bienvenido, <strong><%= usuarioLogado.getUsername() %></strong>
    </div>

    <table>
        <tr>
            <th>ID</th>
            <th>Usuario</th>
            <th>Rol</th>
            <th>Acciones</th>
        </tr>

        <%
            @SuppressWarnings("unchecked")
            List<Usuario> usuarios = (List<Usuario>) request.getAttribute("USUARIOS");
            
            if (usuarios != null && !usuarios.isEmpty()) {
                for (Usuario u : usuarios) {
        %>
        <tr>
            <td><%= u.getIdUsuario() %></td>
            <td><%= u.getUsername() %></td>
            <td><%= u.getRol() %></td>
            <td>
            	<a href="ControlUsuarios?ACTION_ID=BORRAR_USUARIO&id=<%= u.getIdUsuario() %>">[Borrar]</a>
            </td>
        </tr>
        <%
                }
            } else {
                out.println("<tr><td colspan='4'>No hay usuarios registrados</td></tr>");
            }
        %>
    </table>
</body>
</html>