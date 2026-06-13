package edu.ucam.mvc.profesor;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import edu.ucam.bd.ConexionBD;
import edu.ucam.mvc.Accion;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class InsertarProfesor extends Accion {

    @Override
    public void ejecutar(HttpServletRequest request, HttpServletResponse response) throws IOException {
        
        String nombre = request.getParameter("nombre");
        String apellidos = request.getParameter("apellidos"); 
        String departamento = request.getParameter("departamento");
        String correo = request.getParameter("correo");
        
        if (nombre == null || nombre.trim().isEmpty() || apellidos == null || apellidos.trim().isEmpty()) {
            response.sendRedirect("ControlProfesores?ACTION_ID=LISTAR_PROFESORES");
            return;
        }
        
        try (Connection conexion = ConexionBD.getConexion();
             PreparedStatement ps = conexion.prepareStatement(
                 "INSERT INTO profesores (NOMBRE, APELLIDOS, DEPARTAMENTO, CORREO) VALUES (?, ?, ?, ?)")) {
            
            ps.setString(1, nombre); 
            ps.setString(2, apellidos);
            ps.setString(3, departamento);
            ps.setString(4, correo);
            ps.executeUpdate();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        response.sendRedirect("ControlProfesores?ACTION_ID=LISTAR_PROFESORES");
    }
}