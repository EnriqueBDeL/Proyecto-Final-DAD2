package edu.ucam.mvc.titulacion;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import edu.ucam.bd.ConexionBD;
import edu.ucam.mvc.Accion;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class EditarTitulacion extends Accion {

    @Override
    public void ejecutar(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String id = request.getParameter("id");
        String nombre = request.getParameter("nombre");
        String facultad = request.getParameter("facultad");
        String creditosStr = request.getParameter("creditos");

        if (id != null && nombre != null && facultad != null && creditosStr != null) {

            try {
                int creditos = Integer.parseInt(creditosStr);

                try (Connection conexion = ConexionBD.getConexion();
                     PreparedStatement ps = conexion.prepareStatement(
                             "UPDATE titulaciones SET NOMBRE = ?, FACULTAD = ?, CREDITOS = ? WHERE ID = ?")) {

                    ps.setString(1, nombre);
                    ps.setString(2, facultad);
                    ps.setInt(3, creditos);
                    ps.setString(4, id);

                    ps.executeUpdate();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        response.sendRedirect("ControlTitulaciones?ACTION_ID=LISTAR_TITULACIONES");
    }
}