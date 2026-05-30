package edu.ucam.mvc.asignatura;

import java.io.IOException;
import java.sql.*;

import edu.ucam.bd.ConexionBD;
import edu.ucam.mvc.Accion;
import jakarta.servlet.http.*;

public class BorrarAsignatura extends Accion {

    @Override
    public void ejecutar(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id = request.getParameter("id");

        if (id != null && !id.trim().isEmpty()) {
            try (Connection conexion = ConexionBD.getConexion();
                 PreparedStatement ps = conexion.prepareStatement(
                     "DELETE FROM asignaturas WHERE ID=?")) {
                ps.setString(1, id);
                ps.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        response.sendRedirect("ControlAsignaturas?ACTION_ID=LISTAR_ASIGNATURAS");
    }
}