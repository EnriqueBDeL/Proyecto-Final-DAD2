package edu.ucam.mvc.usuario;

import java.io.IOException;
import java.sql.*;

import edu.ucam.bd.ConexionBD;
import edu.ucam.domain.Usuario;
import edu.ucam.mvc.Accion;
import jakarta.servlet.http.*;

public class BorrarUsuario extends Accion {

    @Override
    public void ejecutar(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id = request.getParameter("id");

        // Proteger: no puede borrar su propia cuenta
        HttpSession session = request.getSession(false);
        if (session != null && id != null) {
            Usuario usuarioActual = (Usuario) session.getAttribute("usuario");
            if (usuarioActual != null && String.valueOf(usuarioActual.getIdUsuario()).equals(id)) {
                // No permitir auto-eliminación
                response.sendRedirect("ControlUsuarios?ACTION_ID=LISTAR_USUARIOS&error=autoeliminacion");
                return;
            }
        }

        if (id != null && !id.trim().isEmpty()) {
            try (Connection conexion = ConexionBD.getConexion();
                 PreparedStatement ps = conexion.prepareStatement(
                     "DELETE FROM usuarios WHERE ID_USERNAME=?")) {
                ps.setString(1, id);
                ps.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        response.sendRedirect("ControlUsuarios?ACTION_ID=LISTAR_USUARIOS");
    }
}