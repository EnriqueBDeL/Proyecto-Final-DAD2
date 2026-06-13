<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="edu.ucam.domain.Profesor"%>
<%@ page import="java.util.Hashtable"%>
<%@ page import="java.net.URLEncoder"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Control de Profesores</title>
</head>
<body>

<%
    String idEditar = request.getParameter("idEditar");
    boolean editando = idEditar != null;
%>

<h1><%= editando ? "Editar Profesor" : "Añadir Profesor" %></h1>

<form action="ControlProfesores" method="POST">
    <input type="hidden" name="ACTION_ID"
           value="<%= editando ? "EDITAR_PROFESOR" : "INSERTAR_PROFESOR" %>">

    <% if (editando) { %>
        <input type="hidden" name="id" value="<%= idEditar %>">
    <% } %>

    Nombre:
    <input type="text" name="nombre"
           value="<%= editando ? request.getParameter("nombre") : "" %>" required><br>

    Apellidos:
    <input type="text" name="apellidos"
           value="<%= editando ? request.getParameter("apellidos") : "" %>" required><br>

    Departamento:
    <input type="text" name="departamento"
           value="<%= editando ? request.getParameter("departamento") : "" %>" required><br>

    Correo:
    <input type="email" name="correo"
           value="<%= editando ? request.getParameter("correo") : "" %>" required><br>

    <input type="submit" value="<%= editando ? "Guardar cambios" : "Guardar" %>">

    <% if (editando) { %>
        <a href="ControlProfesores?ACTION_ID=LISTAR_PROFESORES">Cancelar</a>
    <% } %>
</form>

<br><br>
    
<h2>Lista de profesores:</h2>
    
<%
    @SuppressWarnings("unchecked")
    Hashtable<String, Profesor> registro = (Hashtable<String, Profesor>) request.getAttribute("PROFESORES");

    if (registro != null && !registro.isEmpty()) {
        for (Profesor p : registro.values()) {
            
            out.println("<br><b>" + p.getIdProfesor() + " - " + p.getApellidos() + ", " + p.getNombre() + "</b>");
            out.println(" | Dpto: " + p.getDepartamento() + " | Email: " + p.getCorreo());
            
 %>
 			<a href="ControlProfesores?ACTION_ID=LISTAR_PROFESORES&idEditar=<%= p.getIdProfesor() %>&nombre=<%= URLEncoder.encode(p.getNombre(), "UTF-8") %>&apellidos=<%= URLEncoder.encode(p.getApellidos(), "UTF-8") %>&departamento=<%= URLEncoder.encode(p.getDepartamento(), "UTF-8") %>&correo=<%= URLEncoder.encode(p.getCorreo(), "UTF-8") %>">[Editar]</a>
            <a href="ControlProfesores?ACTION_ID=BORRAR_PROFESOR&id=<%= p.getIdProfesor() %>">[Borrar]</a> 
            <%
			        }
			    } else {
			        out.print("<br><b>No hay profesores registrados en la base de datos en este momento.</b>");
			    }
			%>

<br><br>
<a href="menuUsuario.jsp">Volver al Menú Principal</a>

</body>
</html>