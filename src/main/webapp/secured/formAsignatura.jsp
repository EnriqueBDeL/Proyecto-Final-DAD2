<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="edu.ucam.domain.Asignatura"%>
<%@ page import="edu.ucam.domain.Titulacion"%>
<%@ page import="edu.ucam.domain.Profesor"%>
<%@ page import="java.util.List"%>
<%@ page import="java.net.URLEncoder"%>

<%!
    private String valor(String texto) {
        return texto == null ? "" : texto;
    }

    private String selected(String actual, String esperado) {
        return valor(actual).equals(valor(esperado)) ? "selected" : "";
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
    boolean editando = idEditar != null;

    String nombreEditar = request.getParameter("nombre");
    String capacidadEditar = request.getParameter("maxCapacidad");
    String idTitulacionEditar = request.getParameter("idTitulacion");
    String idProfesorEditar = request.getParameter("idProfesor");
%>

<h1><%= editando ? "Editar Asignatura" : "Añadir Asignatura" %></h1>

<form action="ControlAsignaturas" method="POST">
    <input type="hidden" name="ACTION_ID"
           value="<%= editando ? "EDITAR_ASIGNATURA" : "INSERTAR_ASIGNATURA" %>">

    <% if (editando) { %>
        <input type="hidden" name="id" value="<%= idEditar %>">
    <% } %>

    Nombre:
    <input type="text" name="nombre"
           value="<%= editando ? valor(nombreEditar) : "" %>" required><br>

    Capacidad:
    <input type="number" name="maxCapacidad"
           value="<%= editando ? valor(capacidadEditar) : "" %>" required min="1"><br>

    Titulación:
    <select name="idTitulacion" required>
        <option value="">-- Selecciona --</option>

        <% if (titulaciones != null) {
            for (Titulacion t : titulaciones) { %>

                <option value="<%= t.getIdTitulacion() %>"
                    <%= selected(idTitulacionEditar, t.getIdTitulacion()) %>>
                    <%= t.getNombre() %>
                </option>

        <%  }
           } %>
    </select><br>

    Profesor:
		<select name="idProfesor">
		    <option value="">-- Sin Profesor --</option>
		
		    <% if (profesores != null) {
		        for (Profesor p : profesores) { %>
		
		            <option value="<%= p.getIdProfesor() %>"
		                <%= editando && p.getIdProfesor().equals(request.getParameter("idProfesor")) ? "selected" : "" %>>
		                <%= p.getApellidos() %>, <%= p.getNombre() %>
		            </option>
		
		    <%  }
		       } %>
		</select><br>

    <input type="submit" value="<%= editando ? "Guardar cambios" : "Guardar" %>">

    <% if (editando) { %>
        <a href="ControlAsignaturas?ACTION_ID=LISTAR_ASIGNATURAS">Cancelar</a>
    <% } %>
</form>

<br><hr><br>

<h2>Lista de asignaturas:</h2>

<%
if (asignaturas != null && !asignaturas.isEmpty()) {
    for (Asignatura a : asignaturas) {
        String nombreTitulacion = a.getNombreTitulacion() != null ? a.getNombreTitulacion() : "Sin asignar";
        String nombreProfesor = a.getNombreProfesor() != null ? a.getNombreProfesor() : "Sin asignar";

        String idTitulacion = a.getIdTitulacion() != null ? a.getIdTitulacion() : "";
        String idProfesor = a.getIdProfesor() != null ? a.getIdProfesor() : "";

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
<a href="menuUsuario.jsp">Volver al Menú Principal</a>

</body>
</html>