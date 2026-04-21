package edu.ucam.domain;

public class Titulacion {

	private String idTitulacion;
    private String nombre;

    
    public Titulacion(String idTitulacion, String nombre) {
		super();
		this.idTitulacion = idTitulacion;
		this.nombre = nombre;
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
    
    
	
}
