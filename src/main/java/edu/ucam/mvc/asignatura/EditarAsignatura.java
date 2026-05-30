package edu.ucam.mvc.asignatura;

import java.io.IOException;
import java.sql.*;

import edu.ucam.bd.ConexionBD;
import edu.ucam.mvc.Accion;
import jakarta.servlet.http.*;

public class EditarAsignatura extends Accion {

    @Override
    public void ejecutar(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id = request.getParameter("id");
        String nombre = request.getParameter("nombre");
        String capacidadStr = request.getParameter("maxCapacidad");
        String idTitulacion = request.getParameter("idTitulacion");

        if (id == null || nombre == null || nombre.trim().isEmpty()) {
            response.sendRedirect("ControlAsignaturas?ACTION_ID=LISTAR_ASIGNATURAS");
            return;
        }

        int capacidad = 0;
        try { capacidad = Integer.parseInt(capacidadStr); } catch (Exception e) { capacidad = 0; }

        try (Connection conexion = ConexionBD.getConexion();
             PreparedStatement ps = conexion.prepareStatement(
                 "UPDATE asignaturas SET NOMBRE=?, MAX_CAPACIDAD=?, ID_TITULACION=? WHERE ID=?")) {
            ps.setString(1, nombre.trim());
            ps.setInt(2, capacidad);
            if (idTitulacion != null && !idTitulacion.isEmpty()) {
                ps.setString(3, idTitulacion);
            } else {
                ps.setNull(3, Types.INTEGER);
            }
            ps.setString(4, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

        response.sendRedirect("ControlAsignaturas?ACTION_ID=LISTAR_ASIGNATURAS");
    }
}