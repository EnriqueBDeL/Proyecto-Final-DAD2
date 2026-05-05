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

        try {
            Connection conexion = ConexionBD.getConexion();

            String sql = "SELECT * FROM usuarios WHERE username = ? AND password = ?";
            PreparedStatement ps = conexion.prepareStatement(sql);

            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                usuario = new Usuario(
                    rs.getInt("idUsuario"),
                    rs.getString("username"),
                    rs.getString("password"),
                    rs.getString("rol")
                );
            }

            rs.close();
            ps.close();
            conexion.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        if (usuario != null) {
            HttpSession session = request.getSession();
            session.setAttribute("usuario", usuario);

            response.sendRedirect("secured/listarUsuarios.jsp");
        } else {
            request.setAttribute("error", "Usuario o contraseña incorrectos");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}