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

@WebServlet("/ControlProfesores")
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

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String paramActionId = request.getParameter("ACTION_ID");
		
		if (paramActionId == null) {
			System.out.println("No se introdujo ninguna acción.");
		} else {
			Accion accion = acciones.get(paramActionId);
			
			if (accion != null) {
				accion.ejecutar(request, response);
			} else {
				response.getWriter().append("Error: Acción '" + paramActionId + "' no reconocida.");
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}