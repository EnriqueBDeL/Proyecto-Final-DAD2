package edu.ucam.domain;

public class Titulacion {

	private String idTitulacion;
    private String nombre;
    private String facultad;
    private int creditos;
	
    
    public Titulacion(String idTitulacion, String nombre, String facultad, int creditos) {
		super();
		this.idTitulacion = idTitulacion;
		this.nombre = nombre;
		this.facultad = facultad;
		this.creditos = creditos;
	}


	public String getIdTitulacion() {
		return idTitulacion;
	}


	public void setIdTitulacion(String idTitulacion) {
		this.idTitulacion = idTitulacion;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getFacultad() {
		return facultad;
	}


	public void setFacultad(String facultad) {
		this.facultad = facultad;
	}


	public int getCreditos() {
		return creditos;
	}


	public void setCreditos(int creditos) {
		this.creditos = creditos;
	}

    
    
	
}
