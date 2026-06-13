package edu.ucam.mvc.asignatura;

import java.io.IOException;
import java.sql.*;
import edu.ucam.bd.ConexionBD;
import edu.ucam.mvc.Accion;
import jakarta.servlet.http.*;

public class InsertarAsignatura extends Accion {

    @Override
    public void ejecutar(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String nombre = request.getParameter("nombre");
        String capacidadStr = request.getParameter("maxCapacidad");
        String idTitulacion = request.getParameter("idTitulacion");
        String idProfesor = request.getParameter("idProfesor"); 

        if (nombre == null || nombre.trim().isEmpty()) {
            response.sendRedirect("ControlAsignaturas?ACTION_ID=LISTAR_ASIGNATURAS");
            return;
        }

        int capacidad = 0;
        try { capacidad = Integer.parseInt(capacidadStr); } catch (Exception e) { capacidad = 0; }

        String sql = "INSERT INTO asignaturas (NOMBRE, MAX_CAPACIDAD, ID_TITULACION, ID_PROFESOR) VALUES (?, ?, ?, ?)";

        try (Connection conexion = ConexionBD.getConexion();
             PreparedStatement ps = conexion.prepareStatement(sql)) {
            
            ps.setString(1, nombre.trim());
            ps.setInt(2, capacidad);
            
            if (idTitulacion != null && !idTitulacion.isEmpty()) {
                ps.setString(3, idTitulacion);
            } else {
                ps.setNull(3, Types.INTEGER);
            }

            if (idProfesor != null && !idProfesor.isEmpty()) {
                ps.setString(4, idProfesor);
            } else {
                ps.setNull(4, Types.INTEGER);
            }
            
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

        response.sendRedirect("ControlAsignaturas?ACTION_ID=LISTAR_ASIGNATURAS");
    }
}