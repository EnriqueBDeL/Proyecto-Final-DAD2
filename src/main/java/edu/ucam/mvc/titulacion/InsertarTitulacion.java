package edu.ucam.mvc.titulacion;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import edu.ucam.bd.ConexionBD;
import edu.ucam.mvc.Accion;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class InsertarTitulacion extends Accion {

    @Override
    public void ejecutar(HttpServletRequest request, HttpServletResponse response) throws IOException {
        
        String nombre = request.getParameter("nombre");
        String facultad = request.getParameter("facultad");
        String creditosStr = request.getParameter("creditos");
        
        if (nombre == null || nombre.trim().isEmpty()) {
            response.sendRedirect("ControlTitulaciones?ACTION_ID=LISTAR_TITULACIONES");
            return; 
        }

        int creditos = 0; // Pasamos los creditos a int
        if (creditosStr != null && !creditosStr.isEmpty()) {
            try {
                creditos = Integer.parseInt(creditosStr);
            } catch (NumberFormatException e) {
                creditos = 0;
            }
        }
        
        try (Connection conexion = ConexionBD.getConexion();
             PreparedStatement ps = conexion.prepareStatement("INSERT INTO titulaciones (NOMBRE, FACULTAD, CREDITOS) VALUES (?, ?, ?)")) {
            
            ps.setString(1, nombre); 
            ps.setString(2, facultad);
            ps.setInt(3, creditos);
            ps.executeUpdate(); 
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        response.sendRedirect("ControlTitulaciones?ACTION_ID=LISTAR_TITULACIONES");
    }
}