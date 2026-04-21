package edu.ucam.mvc.titulacion;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import edu.ucam.domain.Titulacion;
import edu.ucam.mvc.Accion;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ListarTitulaciones extends Accion {

    @Override
    public void ejecutar(HttpServletRequest request, HttpServletResponse response) throws IOException {
        
        Hashtable<String, Titulacion> titulaciones = new Hashtable<String, Titulacion>();
        
        try {

        	Context initCtx = new InitialContext();
            Context envCtx = (Context) initCtx.lookup("java:comp/env");
            DataSource ds = (DataSource) envCtx.lookup("jdbc/dad2");
            Connection conexion = ds.getConnection();
            
            PreparedStatement ps = conexion.prepareStatement("SELECT * FROM titulaciones");
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()) {
                titulaciones.put(rs.getString("ID"), new Titulacion(rs.getString("ID"), rs.getString("NOMBRE")));               
            }
            
            rs.close();
            ps.close();
            conexion.close();
            
        } catch (NamingException | SQLException e) {
            e.printStackTrace();
        }
        
        try {
            request.setAttribute("TITULACIONES", titulaciones);
            request.getRequestDispatcher("listartitulaciones.jsp").forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }
}