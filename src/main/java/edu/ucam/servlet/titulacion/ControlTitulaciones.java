package edu.ucam.servlet.titulacion;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Hashtable;

import edu.ucam.mvc.Accion;
import edu.ucam.mvc.titulacion.BorrarTitulacion;
import edu.ucam.mvc.titulacion.EditarTitulacion;
import edu.ucam.mvc.titulacion.InsertarTitulacion;
import edu.ucam.mvc.titulacion.ListarTitulaciones;


@WebServlet("/ControlTitulaciones")
public class ControlTitulaciones extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private Hashtable<String, Accion> acciones = null;
  
 
    public ControlTitulaciones() {
        super();
    }
    
    
	@Override
	public void init(ServletConfig config) throws ServletException {
		
		acciones = new Hashtable<String, Accion>();
		
		acciones.put("BORRAR_TITULACION", new BorrarTitulacion());
		acciones.put("EDITAR_TITULACION", new EditarTitulacion());
		acciones.put("LISTAR_TITULACIONES", new ListarTitulaciones());
		acciones.put("INSERTAR_TITULACION", new InsertarTitulacion());
		
		
		super.init(config);
	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

				String paramActionId = request.getParameter("ACTION_ID");
				
				if (paramActionId == null) {
					System.out.println("No se introdujo ninguna acción.");
				}
				
				Accion accion = acciones.get(paramActionId);
				
				if (accion != null) {
					accion.ejecutar(request, response);
				} else {
					response.getWriter().append("Error: Acción '" + paramActionId + "' no reconocida.");
				}
	
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
