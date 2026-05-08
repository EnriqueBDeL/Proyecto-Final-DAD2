package edu.ucam.mvc.titulacion;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

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
        
        
        try {
        	
            Context initCtx = new InitialContext(); // Configuración para obtener la conexión
            Context envCtx = (Context) initCtx.lookup("java:comp/env");
            DataSource ds = (DataSource) envCtx.lookup("jdbc/dad2");// Se obtiene el DataSource configurado en el servidor.
            Connection conexion = ds.getConnection();
            
            
            PreparedStatement ps = conexion.prepareStatement("INSERT INTO titulaciones (NOMBRE, FACULTAD, CREDITOS) VALUES (?, ?, ?)");
            ps.setString(1, nombre); // la base de datos empiea a contar desde 1
            ps.setString(2, facultad);
            ps.setInt(3, creditos);
            ps.executeUpdate(); //operación de ESCRITURA (INSERT, UPDATE, DELETE).
            
            
            // Se liberan los recursos cerrando el PreparedStatement y devolviendo la conexión al pool
            ps.close(); 
            conexion.close();
            
            
        } catch (NamingException | SQLException e) {
            e.printStackTrace();
        }
        
        response.sendRedirect("ControlTitulaciones?ACTION_ID=LISTAR_TITULACIONES");
    }
}