package edu.ucam.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import edu.ucam.bd.ConexionBD;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/UsuarioServlet")
public class UsuarioServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accion");

        if ("eliminar".equals(accion)) {
            eliminarUsuario(request);
        }

        response.sendRedirect("secured/listarUsuarios.jsp");
    }

    private void eliminarUsuario(HttpServletRequest request) {
        try {
            int idUsuario = Integer.parseInt(request.getParameter("idUsuario"));

            Connection conexion = ConexionBD.getConexion();

            String sql = "DELETE FROM usuarios WHERE idUsuario = ?";
            PreparedStatement ps = conexion.prepareStatement(sql);

            ps.setInt(1, idUsuario);
            ps.executeUpdate();

            ps.close();
            conexion.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}