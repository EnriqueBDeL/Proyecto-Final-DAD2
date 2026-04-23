<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="edu.ucam.domain.Titulacion"%>
<%@ page import="java.util.Hashtable"%>
    
<!DOCTYPE html>

<html>


<head>
<meta charset="UTF-8">
<title>Control de titulaciones</title>
</head>


<body>


<h1>Añadir Titulación</h1>

<form action="ControlTitulaciones" method="POST">
		<input type="hidden" name="ACTION_ID" value="INSERTAR_TITULACION"> <!-- Esto manda la clave secreta para que Control escoja cual acción hacer y usar el metodo ejecucion de este. -->
ID:		<input type="text" name="id"><br>
Nombre:	<input type="text" name="nombre"><br>
<input type="submit">
</form>

<br>
<br>
    
    <h2>Lista de titulaciones:</h2>
    
    
    <%
    	Hashtable<String, Titulacion> registro = (Hashtable<String, Titulacion>) request.getAttribute("TITULACIONES");

    if (registro != null && !registro.isEmpty()) {
        
        for (Titulacion t : registro.values()) {
            
            out.println("<br>" + t.getIdTitulacion() + " - " + t.getNombre() + " ");
            
            %>
            <a href="ControlTitulaciones?ACTION_ID=BORRAR_TITULACION&id=<%= t.getIdTitulacion() %>">[Borrar]</a>  
            <%
        }
        
    } else {
        out.print("<br><b>No hay titulaciones registradas en la base de datos en este momento.</b>");
    }
    %>

</body>


</html>