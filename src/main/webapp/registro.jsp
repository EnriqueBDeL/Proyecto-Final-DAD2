<%@ page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <title>Registro</title>
</head>
<body>

<h1>Registro de usuario</h1>

<form action="RegistroServlet" method="post">
    <label>Usuario:</label>
    <input type="text" name="username" required>

    <br><br>

    <label>Contraseña:</label>
    <input type="password" name="password" required>

    <br><br>

    <button type="submit">Registrarse</button>
</form>

<%
    String error = (String) request.getAttribute("error");
    if (error != null) {
%>
    <p style="color:red;"><%= error %></p>
<%
    }
%>

<br>

<a href="login.jsp">Volver al login</a>

</body>
</html>