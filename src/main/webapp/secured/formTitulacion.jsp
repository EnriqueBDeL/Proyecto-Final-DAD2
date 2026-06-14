<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="edu.ucam.domain.Titulacion"%>
<%@ page import="edu.ucam.domain.Usuario" %>
<%@ page import="java.util.Hashtable"%>
<%@ page import="java.net.URLEncoder"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Control de titulaciones</title>
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
    String valorFacultad;
    String valorCreditos;

    if (editando) {
        tituloFormulario = "Editar Titulación";
        accionFormulario = "EDITAR_TITULACION";
        textoBoton = "Guardar cambios";
        
        if (request.getParameter("nombre") != null) {
            valorNombre = request.getParameter("nombre");
        } else {
            valorNombre = "";
        }
        
        if (request.getParameter("facultad") != null) {
            valorFacultad = request.getParameter("facultad");
        } else {
            valorFacultad = "";
        }
        
        if (request.getParameter("creditos") != null) {
            valorCreditos = request.getParameter("creditos");
        } else {
            valorCreditos = "";
        }
        
    } else {
        tituloFormulario = "Añadir Titulación";
        accionFormulario = "INSERTAR_TITULACION";
        textoBoton = "Guardar";
        valorNombre = "";
        valorFacultad = "";
        valorCreditos = "";
    }
%>

<h1><%= tituloFormulario %></h1>

<form action="ControlTitulaciones" method="POST">
    <input type="hidden" name="ACTION_ID" value="<%= accionFormulario %>">
           
    <% if (editando) { %>
        <input type="hidden" name="id" value="<%= idEditar %>">
    <% } %>
	
	Nombre: <input type="text" name="nombre" value="<%= valorNombre %>" required><br>
           
	Facultad: <input type="text" name="facultad" value="<%= valorFacultad %>" required><br>
           
	Créditos: <input type="number" name="creditos" value="<%= valorCreditos %>" required><br>
	
	<input type="submit" value="<%= textoBoton %>">

    <% if (editando) { %>
        <a href="ControlTitulaciones?ACTION_ID=LISTAR_TITULACIONES">Cancelar</a>
    <% } %>
</form>

<br><br>
    
<h2>Lista de titulaciones:</h2>
    
<%
	@SuppressWarnings("unchecked")
    Hashtable<String, Titulacion> registro = (Hashtable<String, Titulacion>) request.getAttribute("TITULACIONES");

    if (registro != null && !registro.isEmpty()) {
        for (Titulacion t : registro.values()) {
            out.println("<br><b>" + t.getIdTitulacion() + " - " + t.getNombre() + "</b>");
            out.println(" | Facultad: " + t.getFacultad() + " | Créditos: " + t.getCreditos());
            
            %>
            <a href="ControlTitulaciones?ACTION_ID=LISTAR_TITULACIONES&idEditar=<%= t.getIdTitulacion() %>&nombre=<%= URLEncoder.encode(t.getNombre(), "UTF-8") %>&facultad=<%= URLEncoder.encode(t.getFacultad(), "UTF-8") %>&creditos=<%= URLEncoder.encode(String.valueOf(t.getCreditos()), "UTF-8") %>">[Editar]</a>
            <a href="ControlTitulaciones?ACTION_ID=BORRAR_TITULACION&id=<%= t.getIdTitulacion() %>">[Borrar]</a>  
            <%
        }
    } else {
        out.print("<br><b>No hay titulaciones registradas en la base de datos en este momento.</b>");
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