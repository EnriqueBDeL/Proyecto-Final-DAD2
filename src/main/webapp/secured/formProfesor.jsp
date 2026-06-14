<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="edu.ucam.domain.Profesor"%>
<%@ page import="edu.ucam.domain.Usuario" %>
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
    
    boolean editando;
    if (idEditar != null) {
        editando = true;
    } else {
        editando = false;
    }
    
    Usuario usuario = (Usuario) session.getAttribute("usuario");

    String tituloFormulario;
    String accionFormulario;
    String textoBoton;
    String valorNombre;
    String valorApellidos;
    String valorDepartamento;
    String valorCorreo;

    if (editando) {
        tituloFormulario = "Editar Profesor";
        accionFormulario = "EDITAR_PROFESOR";
        textoBoton = "Guardar cambios";
        
        if (request.getParameter("nombre") != null) {
            valorNombre = request.getParameter("nombre");
        } else {
            valorNombre = "";
        }
        
        if (request.getParameter("apellidos") != null) {
            valorApellidos = request.getParameter("apellidos");
        } else {
            valorApellidos = "";
        }
        
        if (request.getParameter("departamento") != null) {
            valorDepartamento = request.getParameter("departamento");
        } else {
            valorDepartamento = "";
        }
        
        if (request.getParameter("correo") != null) {
            valorCorreo = request.getParameter("correo");
        } else {
            valorCorreo = "";
        }
        
    } else {
        tituloFormulario = "Añadir Profesor";
        accionFormulario = "INSERTAR_PROFESOR";
        textoBoton = "Guardar";
        valorNombre = "";
        valorApellidos = "";
        valorDepartamento = "";
        valorCorreo = "";
    }
%>

<h1><%= tituloFormulario %></h1>

<% 
    String error = request.getParameter("error");
    if (error != null) {
        if (error.equals("tiene_asignaturas")) {
%>
            <p style="color: red; font-weight: bold;">No se puede borrar este profesor porque tiene asignaturas impartidas. Por favor, modifique o borre sus asignaturas primero. </p>
<% 
        }
    } 
%>

<form action="ControlProfesores" method="POST">
    <input type="hidden" name="ACTION_ID" value="<%= accionFormulario %>">

    <% if (editando) { %>
        <input type="hidden" name="id" value="<%= idEditar %>">
    <% } %>

    Nombre: <input type="text" name="nombre" value="<%= valorNombre %>" required><br>

    Apellidos: <input type="text" name="apellidos" value="<%= valorApellidos %>" required><br>

    Departamento: <input type="text" name="departamento" value="<%= valorDepartamento %>" required><br>

    Correo: <input type="email" name="correo" value="<%= valorCorreo %>" required><br>

    <input type="submit" value="<%= textoBoton %>">

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
<%
    String enlaceMenu;

    if (usuario.isAdmin()) {
        enlaceMenu = request.getContextPath() + "/secured/admin/menuAdmin.jsp";
    } else {
        enlaceMenu = request.getContextPath() + "/secured/menuUsuario.jsp";
    }
%>

<a href="<%= enlaceMenu %>">Volver al Menú Principal</a>

</body>
</html>