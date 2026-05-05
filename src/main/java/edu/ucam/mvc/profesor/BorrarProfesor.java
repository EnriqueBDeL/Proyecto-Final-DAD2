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

public class BorrarProfesor extends Accion {

    @Override
    public void ejecutar(HttpServletRequest request, HttpServletResponse response) throws IOException {
        
        String id = request.getParameter("id");
        
        if (id != null && !id.trim().isEmpty()) {
            try {
                Context initCtx = new InitialContext();
                Context envCtx = (Context) initCtx.lookup("java:comp/env");
                DataSource ds = (DataSource) envCtx.lookup("jdbc/dad2");
                Connection conexion = ds.getConnection();
                
                PreparedStatement ps = conexion.prepareStatement("DELETE FROM profesores WHERE ID = ?");
                ps.setString(1, id);
                ps.executeUpdate();
                
                ps.close();
                conexion.close();
                
            } catch (NamingException | SQLException e) {
                e.printStackTrace();
            }
        }
        
        response.sendRedirect("ControlProfesores?ACTION_ID=LISTAR_PROFESORES");
    }
}