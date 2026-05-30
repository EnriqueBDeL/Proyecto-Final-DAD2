package edu.ucam.mvc.asignatura;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import edu.ucam.bd.ConexionBD;
import edu.ucam.domain.Asignatura;
import edu.ucam.domain.Titulacion;
import edu.ucam.domain.Profesor;
import edu.ucam.mvc.Accion;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;

public class ListarAsignaturas extends Accion {

    @Override
    public void ejecutar(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<Asignatura> asignaturas = new ArrayList<>();
        List<Titulacion> titulaciones = new ArrayList<>();
        List<Profesor> profesores = new ArrayList<>();

        try (Connection conexion = ConexionBD.getConexion()) {

            // Cargar asignaturas con JOIN para obtener nombres
            String sql = "SELECT a.ID, a.NOMBRE, a.MAX_CAPACIDAD, " +
                         "a.ID_TITULACION, t.NOMBRE AS NOM_TIT, " +
                         "a.ID_PROFESOR, CONCAT(p.NOMBRE,' ',p.APELLIDOS) AS NOM_PROF " +
                         "FROM asignaturas a " +
                         "LEFT JOIN titulaciones t ON a.ID_TITULACION = t.ID " +
                         "LEFT JOIN profesores p ON a.ID_PROFESOR = p.ID " +
                         "ORDER BY a.NOMBRE";
            try (PreparedStatement ps = conexion.prepareStatement(sql);
                 ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    asignaturas.add(new Asignatura(
                        rs.getString("ID"),
                        rs.getString("NOMBRE"),
                        rs.getInt("MAX_CAPACIDAD"),
                        rs.getString("ID_TITULACION"),
                        rs.getString("NOM_TIT"),
                        rs.getString("ID_PROFESOR"),
                        rs.getString("NOM_PROF")
                    ));
                }
            }

            // Cargar listas para los selects del formulario
            try (PreparedStatement ps2 = conexion.prepareStatement("SELECT * FROM titulaciones ORDER BY NOMBRE");
                 ResultSet rs2 = ps2.executeQuery()) {
                while (rs2.next()) {
                    titulaciones.add(new Titulacion(
                        rs2.getString("ID"), rs2.getString("NOMBRE"),
                        rs2.getString("FACULTAD"), rs2.getInt("CREDITOS")));
                }
            }

            try (PreparedStatement ps3 = conexion.prepareStatement(
                     "SELECT * FROM profesores ORDER BY APELLIDOS, NOMBRE");
                 ResultSet rs3 = ps3.executeQuery()) {
                while (rs3.next()) {
                    profesores.add(new Profesor(
                        rs3.getString("ID"), rs3.getString("NOMBRE"),
                        rs3.getString("APELLIDOS"), rs3.getString("DEPARTAMENTO"),
                        rs3.getString("CORREO")));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            request.setAttribute("ASIGNATURAS", asignaturas);
            request.setAttribute("TITULACIONES", titulaciones);
            request.setAttribute("PROFESORES", profesores);
            request.getRequestDispatcher("/secured/formAsignatura.jsp").forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }
}