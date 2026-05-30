package edu.ucam.mvc.asignatura;

import java.io.IOException;
import java.sql.*;

import edu.ucam.bd.ConexionBD;
import edu.ucam.mvc.Accion;
import jakarta.servlet.http.*;


public class AsignarProfesor extends Accion {

    @Override
    public void ejecutar(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String idAsignatura = request.getParameter("idAsignatura");
        String idProfesor   = request.getParameter("idProfesor");   // puede ser "" para desasignar

        if (idAsignatura == null || idAsignatura.trim().isEmpty()) {
            response.sendRedirect("ControlAsignaturas?ACTION_ID=LISTAR_ASIGNATURAS");
            return;
        }

        try (Connection conexion = ConexionBD.getConexion();
             PreparedStatement ps = conexion.prepareStatement(
                 "UPDATE asignaturas SET ID_PROFESOR=? WHERE ID=?")) {

            if (idProfesor != null && !idProfesor.trim().isEmpty()) {
                ps.setString(1, idProfesor.trim());
            } else {
                ps.setNull(1, Types.INTEGER);
            }
            ps.setString(2, idAsignatura.trim());
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }

        response.sendRedirect("ControlAsignaturas?ACTION_ID=LISTAR_ASIGNATURAS");
    }
}