<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="edu.ucam.domain.Profesor"%>
<%@ page import="java.util.Hashtable"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Control de Profesores</title>
</head>
<body>

<h1>Añadir Profesor</h1>

<form action="ControlProfesores" method="POST">
	<input type="hidden" name="ACTION_ID" value="INSERTAR_PROFESOR">
	
	Nombre:       <input type="text" name="nombre" required><br>
	Apellidos:    <input type="text" name="apellidos" required><br>
	Departamento: <input type="text" name="departamento"><br>
	Correo:       <input type="email" name="correo"><br>
	
	<input type="submit" value="Guardar">
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
            <a href="ControlProfesores?ACTION_ID=BORRAR_PROFESOR&id=<%= p.getIdProfesor() %>">[Borrar]</a>  
            <%
        }
    } else {
        out.print("<br><b>No hay profesores registrados en la base de datos en este momento.</b>");
    }
%>

</body>
</html>