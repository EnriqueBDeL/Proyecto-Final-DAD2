package edu.ucam.mvc.usuario;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import edu.ucam.bd.ConexionBD;
import edu.ucam.domain.Usuario;
import edu.ucam.mvc.Accion;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;

public class ListarUsuarios extends Accion {

    @Override
    public void ejecutar(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<Usuario> usuarios = new ArrayList<>();

        try (Connection conexion = ConexionBD.getConexion();
             PreparedStatement ps = conexion.prepareStatement(
                 "SELECT * FROM usuarios ORDER BY USERNAME");
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                usuarios.add(new Usuario(
                    rs.getInt("ID_USERNAME"),
                    rs.getString("USERNAME"),
                    rs.getString("PASSWORD"),
                    rs.getString("ROL")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            request.setAttribute("USUARIOS", usuarios);
            request.getRequestDispatcher("/secured/admin/formUsuario.jsp").forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }
}