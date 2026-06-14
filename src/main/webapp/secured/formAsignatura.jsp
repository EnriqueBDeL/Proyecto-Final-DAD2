<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="edu.ucam.domain.Asignatura"%>
<%@ page import="edu.ucam.domain.Titulacion"%>
<%@ page import="edu.ucam.domain.Profesor"%>
<%@ page import="edu.ucam.domain.Usuario" %>
<%@ page import="java.util.List"%>
<%@ page import="java.net.URLEncoder"%>

<%!

	private String valor(String texto) {
        if (texto == null) {
            return "";
        } else {
            return texto;
        }
    }

    private String selected(String actual, String esperado) {
        if (valor(actual).equals(valor(esperado))) {
            return "selected";
        } else {
            return "";
        }
    }
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Control de Asignaturas</title>
</head>
<body>

<%
    @SuppressWarnings("unchecked")
    List<Asignatura> asignaturas = (List<Asignatura>) request.getAttribute("ASIGNATURAS");

    @SuppressWarnings("unchecked")
    List<Titulacion> titulaciones = (List<Titulacion>) request.getAttribute("TITULACIONES");

    @SuppressWarnings("unchecked")
    List<Profesor> profesores = (List<Profesor>) request.getAttribute("PROFESORES");

    String idEditar = request.getParameter("idEditar");
    
    boolean editando;
    if (idEditar != null) {
        editando = true;
    } else {
        editando = false;
    }

    String nombreEditar = request.getParameter("nombre");
    String capacidadEditar = request.getParameter("maxCapacidad");
    String idTitulacionEditar = request.getParameter("idTitulacion");
    String idProfesorEditar = request.getParameter("idProfesor");
    Usuario usuario = (Usuario) session.getAttribute("usuario");

    String tituloFormulario;
    String accionFormulario;
    String textoBoton;
    String valorNombre;
    String valorCapacidad;

    if (editando) {
        tituloFormulario = "Editar Asignatura";
        accionFormulario = "EDITAR_ASIGNATURA";
        textoBoton = "Guardar cambios";
        valorNombre = valor(nombreEditar);
        valorCapacidad = valor(capacidadEditar);
    } else {
        tituloFormulario = "Añadir Asignatura";
        accionFormulario = "INSERTAR_ASIGNATURA";
        textoBoton = "Guardar";
        valorNombre = "";
        valorCapacidad = "";
    }
%>

<h1><%= tituloFormulario %></h1>

<form action="ControlAsignaturas" method="POST">
    <input type="hidden" name="ACTION_ID" value="<%= accionFormulario %>">

    <% if (editando) { %>
        <input type="hidden" name="id" value="<%= idEditar %>">
    <% } %>

    Nombre:
    <input type="text" name="nombre" value="<%= valorNombre %>" required><br>

    Capacidad:
    <input type="number" name="maxCapacidad" value="<%= valorCapacidad %>" required min="1"><br>

    Titulación:
    <select name="idTitulacion" required>
        <option value="">-- Selecciona --</option>

        <% if (titulaciones != null) {
            for (Titulacion t : titulaciones) { %>

                <option value="<%= t.getIdTitulacion() %>" <%= selected(idTitulacionEditar, t.getIdTitulacion()) %>>
                    <%= t.getNombre() %>
                </option>

        <%  }
           } %>
    </select><br>

    Profesor:
		<select name="idProfesor">
		    <option value="">-- Sin Profesor --</option>
		
		    <% if (profesores != null) {
		        for (Profesor p : profesores) { 
                    
                    String seleccionadoProfesor;
                    if (editando && p.getIdProfesor().equals(request.getParameter("idProfesor"))) {
                        seleccionadoProfesor = "selected";
                    } else {
                        seleccionadoProfesor = "";
                    }
            %>
		
		            <option value="<%= p.getIdProfesor() %>" <%= seleccionadoProfesor %>>
		                <%= p.getApellidos() %>, <%= p.getNombre() %>
		            </option>
		
		    <%  }
		       } %>
		</select><br>

    <input type="submit" value="<%= textoBoton %>">

    <% if (editando) { %>
        <a href="ControlAsignaturas?ACTION_ID=LISTAR_ASIGNATURAS">Cancelar</a>
    <% } %>
</form>

<br><hr><br>

<h2>Lista de asignaturas:</h2>

<%
if (asignaturas != null && !asignaturas.isEmpty()) {
    for (Asignatura a : asignaturas) {
        
        String nombreTitulacion;
        if (a.getNombreTitulacion() != null) {
            nombreTitulacion = a.getNombreTitulacion();
        } else {
            nombreTitulacion = "Sin asignar";
        }

        String nombreProfesor;
        if (a.getNombreProfesor() != null) {
            nombreProfesor = a.getNombreProfesor();
        } else {
            nombreProfesor = "Sin asignar";
        }

        String idTitulacion;
        if (a.getIdTitulacion() != null) {
            idTitulacion = a.getIdTitulacion();
        } else {
            idTitulacion = "";
        }

        String idProfesor;
        if (a.getIdProfesor() != null) {
            idProfesor = a.getIdProfesor();
        } else {
            idProfesor = "";
        }

        out.println("<br><b>" + a.getIdAsignatura() + " - " + a.getNombre() + "</b>");
        out.println(" | Titulación: " + nombreTitulacion + " | Capacidad: " + a.getMaxCapacidad());
        out.println(" | Profesor: " + nombreProfesor);
%>

        <a href="ControlAsignaturas?ACTION_ID=LISTAR_ASIGNATURAS&idEditar=<%= a.getIdAsignatura() %>&nombre=<%= URLEncoder.encode(a.getNombre(), "UTF-8") %>&maxCapacidad=<%= a.getMaxCapacidad() %>&idTitulacion=<%= URLEncoder.encode(idTitulacion, "UTF-8") %>&idProfesor=<%= URLEncoder.encode(idProfesor, "UTF-8") %>">[Editar]</a>

        <a href="ControlAsignaturas?ACTION_ID=BORRAR_ASIGNATURA&id=<%= a.getIdAsignatura() %>">[Borrar]</a><br>

<%
    }
} else {
    out.print("<br><b>No hay asignaturas registradas.</b>");
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