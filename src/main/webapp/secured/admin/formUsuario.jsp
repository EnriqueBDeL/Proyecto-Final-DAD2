<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="edu.ucam.domain.Usuario" %>
<%@ page import="java.util.List"%>
    
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="UTF-8">
<title>Control de Usuarios</title>
</head>
<body>

<%
    String idEditar = request.getParameter("idEditar");
    
    boolean editando;
    if (idEditar != null) {
        editando = true;
    } else {
        editando = false;
    }
    
    String tituloFormulario;
    String accionFormulario;
    String textoBoton;
    String valorUsername;
    String valorRol;

    if (editando) {
        tituloFormulario = "Editar Usuario";
        accionFormulario = "EDITAR_USUARIO";
        textoBoton = "Guardar cambios";
        
        if (request.getParameter("username") != null) {
            valorUsername = request.getParameter("username");
        } else {
            valorUsername = "";
        }
        
        if (request.getParameter("rol") != null) {
            valorRol = request.getParameter("rol");
        } else {
            valorRol = "USER";
        }
        
    } else {
        tituloFormulario = "Añadir Usuario";
        accionFormulario = "INSERTAR_USUARIO";
        textoBoton = "Guardar";
        valorUsername = "";
        valorRol = "USER";
    }
%>

<h1><%= tituloFormulario %></h1>

<form action="ControlUsuarios" method="POST">
    <input type="hidden" name="ACTION_ID" value="<%= accionFormulario %>">

    <% if (editando) { %>
        <input type="hidden" name="id" value="<%= idEditar %>">
    <% } %>

    Usuario: <input type="text" name="username" value="<%= valorUsername %>" required><br>

    Contraseña: <input type="password" name="password" <% if (!editando) { out.print("required"); } %>> 
    <% if (editando) { out.print("<i>(Deja en blanco para no cambiarla)</i>"); } %><br>

    Rol:
    <select name="rol">
        <% 
            String selAdmin = "";
            String selUser = "";
            if (valorRol.equals("ADMIN")) {
                selAdmin = "selected";
            } else {
                selUser = "selected";
            }
        %>
        <option value="USER" <%= selUser %>>Usuario Normal</option>
        <option value="ADMIN" <%= selAdmin %>>Administrador</option>
    </select><br>

    <input type="submit" value="<%= textoBoton %>">

    <% if (editando) { %>
        <a href="ControlUsuarios?ACTION_ID=LISTAR_USUARIOS">Cancelar</a>
    <% } %>
</form>

<br><br>
    
<h2>Lista de usuarios registrados:</h2>
    
<%
    @SuppressWarnings("unchecked")
    List<Usuario> usuarios = (List<Usuario>) request.getAttribute("USUARIOS");

    if (usuarios != null && !usuarios.isEmpty()) {
        for (Usuario u : usuarios) {
            out.println("<br><b>ID: " + u.getIdUsuario() + " - " + u.getUsername() + "</b>");
            out.println(" | Rol: " + u.getRol());
            
 %>
 			<a href="ControlUsuarios?ACTION_ID=LISTAR_USUARIOS&idEditar=<%= u.getIdUsuario() %>&username=<%= u.getUsername() %>&rol=<%= u.getRol() %>">[Editar]</a>
            <a href="ControlUsuarios?ACTION_ID=BORRAR_USUARIO&id=<%= u.getIdUsuario() %>">[Borrar]</a> 
            <%
        }
    } else {
        out.print("<br><b>No hay usuarios registrados en la base de datos.</b>");
    }
%>

<br><br>
<a href="menuAdmin.jsp">Volver al Menú Principal</a>

</body>
</html>