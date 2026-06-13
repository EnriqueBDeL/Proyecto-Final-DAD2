package edu.ucam.mvc.titulacion;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Hashtable;

import edu.ucam.bd.ConexionBD;
import edu.ucam.domain.Titulacion;
import edu.ucam.mvc.Accion;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ListarTitulaciones extends Accion {

    @Override
    public void ejecutar(HttpServletRequest request, HttpServletResponse response) throws IOException {
        
        Hashtable<String, Titulacion> titulaciones = new Hashtable<String, Titulacion>();
        
        try (Connection conexion = ConexionBD.getConexion();
             PreparedStatement ps = conexion.prepareStatement("SELECT * FROM titulaciones");
             ResultSet rs = ps.executeQuery()) {
            
            while(rs.next()) {
            	Titulacion t = new Titulacion( rs.getString("ID"), rs.getString("NOMBRE"), rs.getString("FACULTAD"), rs.getInt("CREDITOS"));
                titulaciones.put(rs.getString("ID"), t);               
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        try {
            request.setAttribute("TITULACIONES", titulaciones);
            request.getRequestDispatcher("formTitulacion.jsp").forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }
}