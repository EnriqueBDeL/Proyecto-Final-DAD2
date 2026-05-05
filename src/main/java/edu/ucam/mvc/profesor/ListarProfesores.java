package edu.ucam.mvc.profesor;

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

import edu.ucam.domain.Profesor;
import edu.ucam.mvc.Accion;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ListarProfesores extends Accion {

    @Override
    public void ejecutar(HttpServletRequest request, HttpServletResponse response) throws IOException {
        
        Hashtable<String, Profesor> profesores = new Hashtable<String, Profesor>();
        
        try {
            Context initCtx = new InitialContext();
            Context envCtx = (Context) initCtx.lookup("java:comp/env");
            DataSource ds = (DataSource) envCtx.lookup("jdbc/dad2");
            Connection conexion = ds.getConnection();
            
            PreparedStatement ps = conexion.prepareStatement("SELECT * FROM profesores");
            ResultSet rs = ps.executeQuery();

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

            rs.close();
            ps.close();
            conexion.close();
            
        } catch (NamingException | SQLException e) {
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