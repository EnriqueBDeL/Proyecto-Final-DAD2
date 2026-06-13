package edu.ucam.mvc.profesor;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import edu.ucam.bd.ConexionBD;
import edu.ucam.mvc.Accion;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class BorrarProfesor extends Accion {

    @Override
    public void ejecutar(HttpServletRequest request, HttpServletResponse response) throws IOException {
        
        String id = request.getParameter("id");
        
        if (id != null && !id.trim().isEmpty()) {
            try (Connection conexion = ConexionBD.getConexion();
                 PreparedStatement ps = conexion.prepareStatement("DELETE FROM profesores WHERE ID = ?")) {
                
                ps.setString(1, id);
                ps.executeUpdate();
                
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        response.sendRedirect("ControlProfesores?ACTION_ID=LISTAR_PROFESORES");
    }
}