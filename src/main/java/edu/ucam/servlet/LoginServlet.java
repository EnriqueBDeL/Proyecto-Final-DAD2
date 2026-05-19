package edu.ucam.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import edu.ucam.bd.ConexionBD;
import edu.ucam.domain.Usuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        System.out.println("Usuario recibido: " + username);
        System.out.println("Password recibida: " + password);


        Usuario usuario = null;

        String sql = "SELECT * FROM usuarios WHERE username = ? AND password = ?";

        try (Connection conexion = ConexionBD.getConexion();
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                usuario = new Usuario(
                    rs.getInt("ID_USERNAME"),
                    rs.getString("USERNAME"),
                    rs.getString("PASSWORD"),
                    rs.getString("ROL")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        if (usuario != null) {
            request.getSession().setAttribute("usuario", usuario);
            response.sendRedirect("secured/listarUsuarios.jsp");
        } else {
            request.setAttribute("error", "Usuario o contraseña incorrectos");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}