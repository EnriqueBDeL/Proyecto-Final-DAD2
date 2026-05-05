package edu.ucam.mvc.profesor;

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
        
        try {
            Context initCtx = new InitialContext();
            Context envCtx = (Context) initCtx.lookup("java:comp/env");
            DataSource ds = (DataSource) envCtx.lookup("jdbc/dad2");
            Connection conexion = ds.getConnection();
            
            PreparedStatement ps = conexion.prepareStatement(
                "INSERT INTO profesores (NOMBRE, APELLIDOS, DEPARTAMENTO, CORREO) VALUES (?, ?, ?, ?)"
            );
            ps.setString(1, nombre); 
            ps.setString(2, apellidos);
            ps.setString(3, departamento);
            ps.setString(4, correo);
            ps.executeUpdate();
            
            ps.close();
            conexion.close();
            
        } catch (NamingException | SQLException e) {
            e.printStackTrace();
        }
        
        response.sendRedirect("ControlProfesores?ACTION_ID=LISTAR_PROFESORES");
    }
}