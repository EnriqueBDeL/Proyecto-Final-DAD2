package edu.ucam.servlet.usuario;

import java.io.IOException;
import java.util.Hashtable;

import edu.ucam.mvc.Accion;
import edu.ucam.mvc.usuario.*;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/secured/admin/ControlUsuarios")
public class ControlUsuarios extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private Hashtable<String, Accion> acciones;

    @Override
    public void init(ServletConfig config) throws ServletException {
        acciones = new Hashtable<>();
        acciones.put("LISTAR_USUARIOS",  new ListarUsuarios());
        acciones.put("INSERTAR_USUARIO", new InsertarUsuario());
        acciones.put("EDITAR_USUARIO",   new EditarUsuario());
        acciones.put("BORRAR_USUARIO",   new BorrarUsuario());
        super.init(config);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        despachar(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        despachar(request, response);
    }

    private void despachar(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String actionId = request.getParameter("ACTION_ID");
        if (actionId == null) actionId = "LISTAR_USUARIOS";
        Accion accion = acciones.get(actionId);
        if (accion != null) {
            accion.ejecutar(request, response);
        } else {
            try { response.getWriter().println("Acción desconocida: " + actionId); }
            catch (Exception e) { e.printStackTrace(); }
        }
    }
}