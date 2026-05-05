package edu.ucam.domain;

public class Profesor {
	
	private String idProfesor;
    private String nombre;
    private String apellidos;
    private String departamento; 
    private String correo;
	
    
    public Profesor(String idProfesor, String nombre, String apellidos, String departamento, String correo) {
		super();
		this.idProfesor = idProfesor;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.departamento = departamento;
		this.correo = correo;
	}


	public String getIdProfesor() {
		return idProfesor;
	}


	public void setIdProfesor(String idProfesor) {
		this.idProfesor = idProfesor;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getApellidos() {
		return apellidos;
	}


	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}


	public String getDepartamento() {
		return departamento;
	}


	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}


	public String getCorreo() {
		return correo;
	}


	public void setCorreo(String correo) {
		this.correo = correo;
	}       

    
    
    
    
}