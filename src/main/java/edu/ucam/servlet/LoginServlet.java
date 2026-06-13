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

        Usuario usuario = null;

        String sql = "SELECT ID_USERNAME, USERNAME, PASSWORD, ROL FROM usuarios WHERE USERNAME = ? AND PASSWORD = ?";

        try (Connection conexion = ConexionBD.getConexion();
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setString(1, username);
            ps.setString(2, password);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {
                    usuario = new Usuario(
                            rs.getInt("ID_USERNAME"),
                            rs.getString("USERNAME"),
                            rs.getString("PASSWORD"),
                            rs.getString("ROL")
                    );
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        if (usuario != null) {
            HttpSession session = request.getSession(true);
            session.setAttribute("usuario", usuario);

            if (usuario.isAdmin()) {
                response.sendRedirect(request.getContextPath() + "/secured/admin/menuAdmin.jsp");
            } else {
                response.sendRedirect(request.getContextPath() + "/secured/menuUsuario.jsp");
            }

        } else {
            request.setAttribute("error", "Usuario o contraseña incorrectos");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }
    }
}