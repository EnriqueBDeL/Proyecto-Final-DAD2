package edu.ucam.mvc.profesor;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Hashtable;

import edu.ucam.bd.ConexionBD; 
import edu.ucam.domain.Profesor;
import edu.ucam.mvc.Accion;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ListarProfesores extends Accion {

    @Override
    public void ejecutar(HttpServletRequest request, HttpServletResponse response) throws IOException {
        
        Hashtable<String, Profesor> profesores = new Hashtable<String, Profesor>();
        
      
        try (Connection conexion = ConexionBD.getConexion();
             PreparedStatement ps = conexion.prepareStatement("SELECT * FROM profesores");
             ResultSet rs = ps.executeQuery()) {

            while(rs.next()) {
                Profesor p = new Profesor(
                    rs.getString("ID"), 
                    rs.getString("NOMBRE"),
                    rs.getString("APELLIDOS"), 
                    rs.getString("DEPARTAMENTO"),
                    rs.getString("CORREO")
                );
                profesores.put(rs.getString("ID"), p);               
            }
            

        } catch (Exception e) { 
            e.printStackTrace();
        }
        
        try {
            request.setAttribute("PROFESORES", profesores);
            request.getRequestDispatcher("formProfesor.jsp").forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }
}