package edu.ucam.mvc.titulacion;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import edu.ucam.bd.ConexionBD;
import edu.ucam.mvc.Accion;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class BorrarTitulacion extends Accion {

    @Override
    public void ejecutar(HttpServletRequest request, HttpServletResponse response) throws IOException {
        
        String id = request.getParameter("id");
        
        if (id != null && !id.trim().isEmpty()) {
            try (Connection conexion = ConexionBD.getConexion()) {
                
                boolean tieneAsignaturas = false;
                try (PreparedStatement psCheck = conexion.prepareStatement("SELECT COUNT(*) FROM asignaturas WHERE ID_TITULACION = ?")) {
                    psCheck.setString(1, id);
                    try (ResultSet rs = psCheck.executeQuery()) {
                        if (rs.next()) {
                            if (rs.getInt(1) > 0) {
                                tieneAsignaturas = true;
                            }
                        }
                    }
                }
                
                if (tieneAsignaturas) {
                    response.sendRedirect("ControlTitulaciones?ACTION_ID=LISTAR_TITULACIONES&error=tiene_asignaturas");
                    return; 
                }

                try (PreparedStatement ps = conexion.prepareStatement("DELETE FROM titulaciones WHERE ID = ?")) {
                    ps.setString(1, id);
                    ps.executeUpdate();
                }
                
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        response.sendRedirect("ControlTitulaciones?ACTION_ID=LISTAR_TITULACIONES");
    }
}