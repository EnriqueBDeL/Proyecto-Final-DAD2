package edu.ucam.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import edu.ucam.bd.ConexionBD;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/RegistroServlet")
public class RegistroServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try {
            Connection conexion = ConexionBD.getConexion();

            String sql = "INSERT INTO usuarios(username, password, rol) VALUES (?, ?, ?)";
            PreparedStatement ps = conexion.prepareStatement(sql);

            ps.setString(1, username);
            ps.setString(2, password);
            ps.setString(3, "USER");

            ps.executeUpdate();

            ps.close();
            conexion.close();

            response.sendRedirect("login.jsp");

        } catch (Exception e) {
            e.printStackTrace();

            request.setAttribute("error", "No se ha podido registrar el usuario");
            request.getRequestDispatcher("registro.jsp").forward(request, response);
        }
    }
}