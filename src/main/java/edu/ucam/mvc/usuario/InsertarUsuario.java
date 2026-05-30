package edu.ucam.mvc.usuario;

import java.io.IOException;
import java.sql.*;

import edu.ucam.bd.ConexionBD;
import edu.ucam.mvc.Accion;
import jakarta.servlet.http.*;

public class InsertarUsuario extends Accion {

    @Override
    public void ejecutar(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String rol = request.getParameter("rol");

        if (username == null || username.trim().isEmpty() || password == null || password.trim().isEmpty()) {
            response.sendRedirect("ControlUsuarios?ACTION_ID=LISTAR_USUARIOS");
            return;
        }
        if (!"ADMIN".equals(rol) && !"USER".equals(rol)) {
            rol = "USER";
        }

        try (Connection conexion = ConexionBD.getConexion();
             PreparedStatement ps = conexion.prepareStatement(
                 "INSERT INTO usuarios (USERNAME, PASSWORD, ROL) VALUES (?, ?, ?)")) {
            ps.setString(1, username.trim());
            ps.setString(2, password);
            ps.setString(3, rol);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

        response.sendRedirect("ControlUsuarios?ACTION_ID=LISTAR_USUARIOS");
    }
}