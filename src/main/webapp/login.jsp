<%@ page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
</head>
<body>

<h1>Iniciar sesión</h1>

<form action="LoginServlet" method="post">
    <label>Usuario:</label>
    <input type="text" name="username" required>

    <br><br>

    <label>Contraseña:</label>
    <input type="password" name="password" required>

    <br><br>

    <button type="submit">Entrar</button>
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

<a href="registro.jsp">Crear cuenta</a>

</body>
</html>