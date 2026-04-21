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
        
        String id = request.getParameter("id");
        String nombre = request.getParameter("nombre");
        
        try {

        	Context initCtx = new InitialContext();
            Context envCtx = (Context) initCtx.lookup("java:comp/env");
            DataSource ds = (DataSource) envCtx.lookup("jdbc/dad2");
            Connection conexion = ds.getConnection();
            
            // Ejecutar SQL (INSERT)
            PreparedStatement ps = conexion.prepareStatement("INSERT INTO titulaciones (ID, NOMBRE) VALUES (?, ?)");
            ps.setString(1, id);
            ps.setString(2, nombre);
            ps.executeUpdate();
            
            // Cerrar conexiones
            ps.close();
            conexion.close();
            
        } catch (NamingException | SQLException e) {
            e.printStackTrace();
        }
        
        response.sendRedirect("control?ACTION_ID=LISTAR_TITULACIONES");
    }
}