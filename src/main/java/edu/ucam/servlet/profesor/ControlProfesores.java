package edu.ucam.servlet.profesor;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Hashtable;

import edu.ucam.mvc.Accion;
import edu.ucam.mvc.profesor.BorrarProfesor;
import edu.ucam.mvc.profesor.EditarProfesor;
import edu.ucam.mvc.profesor.InsertarProfesor;
import edu.ucam.mvc.profesor.ListarProfesores;

@WebServlet("/secured/ControlProfesores") 
public class ControlProfesores extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private Hashtable<String, Accion> acciones = null;
  
    public ControlProfesores() {
        super();
    }
    
	@Override
	public void init(ServletConfig config) throws ServletException {
		acciones = new Hashtable<String, Accion>();
		acciones.put("BORRAR_PROFESOR", new BorrarProfesor());
		acciones.put("EDITAR_PROFESOR", new EditarProfesor());
		acciones.put("LISTAR_PROFESORES", new ListarProfesores());
		acciones.put("INSERTAR_PROFESOR", new InsertarProfesor());
		super.init(config);
	}

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        despachar(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        despachar(request, response);
    }

    private void despachar(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String actionId = request.getParameter("ACTION_ID");
        if (actionId == null) actionId = "LISTAR_PROFESORES";
        
        Accion accion = acciones.get(actionId);
        if (accion != null) {
            accion.ejecutar(request, response);
        } else {
            try { response.getWriter().println("Acción desconocida: " + actionId); }
            catch (Exception e) { e.printStackTrace(); }
        }
    }
}