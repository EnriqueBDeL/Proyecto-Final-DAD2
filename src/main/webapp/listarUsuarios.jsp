<%@ page import="java.sql.*" %>
<%@ page import="edu.ucam.bd.ConexionBD" %>
<%@ page import="edu.ucam.domain.Usuario" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
    Usuario usuarioLogado = (Usuario) session.getAttribute("usuario");

    if (usuarioLogado == null) {
        response.sendRedirect("../login.jsp");
        return;
    }
%>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Listar Usuarios</title>
    <style>

        h1 {
            text-align: center;
        }

        .bienvenida {
            text-align: center;
            margin-bottom: 20px;
        }

        table {
            width: 80%;
            margin: auto;
            border-collapse: collapse;
            background: white;
        }

        th, td {
            padding: 12px;
            border: 1px solid #ccc;
            text-align: center;
        }

        th {
            background: #2c3e50;
            color: white;
        }

        tr:nth-child(even) {
            background: #f2f2f2;
        }

        .logout {
            display: block;
            width: 120px;
            margin: 20px auto;
            padding: 10px;
            text-align: center;
            background: crimson;
            color: white;
            text-decoration: none;
            border-radius: 5px;
        }
    </style>
</head>
<body>

    <h1>Listado de Usuarios</h1>

    <div class="bienvenida">
        Bienvenido, <strong><%= usuarioLogado.getUsername() %></strong>
    </div>

    <table>
        <tr>
            <th>ID</th>
            <th>Usuario</th>
            <th>Contraseña</th>
            <th>Rol</th>
        </tr>

        <%
            try (Connection conexion = ConexionBD.getConexion()) {

                String sql = "SELECT * FROM usuarios";
                PreparedStatement ps = conexion.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();

                while (rs.next()) {
        %>

        <tr>
            <td><%= rs.getInt("ID_USERNAME") %></td>
            <td><%= rs.getString("USERNAME") %></td>
            <td><%= rs.getString("PASSWORD") %></td>
            <td><%= rs.getString("ROL") %></td>
        </tr>

        <%
                }

            } catch (Exception e) {
                out.println("<tr><td colspan='4'>Error al cargar usuarios</td></tr>");
                e.printStackTrace();
            }
        %>