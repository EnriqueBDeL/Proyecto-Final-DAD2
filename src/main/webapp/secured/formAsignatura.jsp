<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="edu.ucam.domain.Asignatura"%>
<%@ page import="edu.ucam.domain.Titulacion"%>
<%@ page import="edu.ucam.domain.Profesor"%>
<%@ page import="java.util.List"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Control de Asignaturas</title>
</head>
<body>

<h1>Añadir Asignatura</h1>

<%
    @SuppressWarnings("unchecked")
    List<Asignatura> asignaturas = (List<Asignatura>) request.getAttribute("ASIGNATURAS");
    @SuppressWarnings("unchecked")
    List<Titulacion> titulaciones = (List<Titulacion>) request.getAttribute("TITULACIONES");
    @SuppressWarnings("unchecked")
    List<Profesor> profesores = (List<Profesor>) request.getAttribute("PROFESORES");
%>

<form action="ControlAsignaturas" method="POST">
    <input type="hidden" name="ACTION_ID" value="INSERTAR_ASIGNATURA">
    
    Nombre: <input type="text" name="nombre" required><br>
    Capacidad: <input type="number" name="maxCapacidad" required min="1"><br>
    
    Titulación: 
    <select name="idTitulacion" required>
        <option value="">-- Selecciona --</option>
        <% if (titulaciones != null) { 
            for (Titulacion t : titulaciones) { %>
                <option value="<%= t.getIdTitulacion() %>"><%= t.getNombre() %></option>
        <%  } 
           } %>
    </select><br>

    Profesor: 
    <select name="idProfesor">
        <option value="">-- Sin Profesor --</option>
        <% if (profesores != null) { 
            for (Profesor p : profesores) { %>
                <option value="<%= p.getIdProfesor() %>"><%= p.getApellidos() %>, <%= p.getNombre() %></option>
        <%  } 
           } %>
    </select><br>
    
    <input type="submit" value="Guardar">
</form>

<br><hr><br>

<h2>Lista de asignaturas:</h2>

<%
if (asignaturas != null && !asignaturas.isEmpty()) {
    for (Asignatura a : asignaturas) {
        String nombreTitulacion = (a.getNombreTitulacion() != null) ? a.getNombreTitulacion() : "Sin asignar";
        String nombreProfesor = (a.getNombreProfesor() != null) ? a.getNombreProfesor() : "Sin asignar";
        
        out.println("<br><b>" + a.getIdAsignatura() + " - " + a.getNombre() + "</b>");
        out.println(" | Titulación: " + nombreTitulacion + " | Capacidad: " + a.getMaxCapacidad());
        out.println(" | Profesor: " + nombreProfesor);
%>
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