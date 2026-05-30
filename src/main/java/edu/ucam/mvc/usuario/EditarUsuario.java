package edu.ucam.mvc.usuario;

import java.io.IOException;
import java.sql.*;

import edu.ucam.bd.ConexionBD;
import edu.ucam.mvc.Accion;
import jakarta.servlet.http.*;

public class EditarUsuario extends Accion {

    @Override
    public void ejecutar(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id = request.getParameter("id");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String rol = request.getParameter("rol");

        if (id == null || username == null || username.trim().isEmpty()) {
            response.sendRedirect("ControlUsuarios?ACTION_ID=LISTAR_USUARIOS");
            return;
        }
        if (!"ADMIN".equals(rol) && !"USER".equals(rol)) {
            rol = "USER";
        }

        try (Connection conexion = ConexionBD.getConexion()) {
            if (password != null && !password.trim().isEmpty()) {
                // Actualizar con contraseña nueva
                try (PreparedStatement ps = conexion.prepareStatement(
                         "UPDATE usuarios SET USERNAME=?, PASSWORD=?, ROL=? WHERE ID_USERNAME=?")) {
                    ps.setString(1, username.trim());
                    ps.setString(2, password);
                    ps.setString(3, rol);
                    ps.setString(4, id);
                    ps.executeUpdate();
                }
            } else {
                // Mantener contraseña actual
                try (PreparedStatement ps = conexion.prepareStatement(
                         "UPDATE usuarios SET USERNAME=?, ROL=? WHERE ID_USERNAME=?")) {
                    ps.setString(1, username.trim());
                    ps.setString(2, rol);
                    ps.setString(3, id);
                    ps.executeUpdate();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        response.sendRedirect("ControlUsuarios?ACTION_ID=LISTAR_USUARIOS");
    }
}