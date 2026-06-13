package edu.ucam.mvc.profesor;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import edu.ucam.bd.ConexionBD;
import edu.ucam.mvc.Accion;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class EditarProfesor extends Accion {

    @Override
    public void ejecutar(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String id = request.getParameter("id");
        String nombre = request.getParameter("nombre");
        String apellidos = request.getParameter("apellidos");
        String departamento = request.getParameter("departamento");
        String correo = request.getParameter("correo");

        if (id != null && nombre != null && apellidos != null && departamento != null && correo != null) {
            try (Connection conexion = ConexionBD.getConexion();
                 PreparedStatement ps = conexion.prepareStatement(
                     "UPDATE profesores SET NOMBRE = ?, APELLIDOS = ?, DEPARTAMENTO = ?, CORREO = ? WHERE ID = ?"
                 )) {

                ps.setString(1, nombre);
                ps.setString(2, apellidos);
                ps.setString(3, departamento);
                ps.setString(4, correo);
                ps.setString(5, id);

                ps.executeUpdate();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        response.sendRedirect("ControlProfesores?ACTION_ID=LISTAR_PROFESORES");
    }
}