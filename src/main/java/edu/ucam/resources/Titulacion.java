package edu.ucam.resources;

public class Titulacion {

	private int idTitulacion;
    private String nombre;

    
    public Titulacion(int idTitulacion, String nombre) {
		super();
		this.idTitulacion = idTitulacion;
		this.nombre = nombre;
	}


	public int getIdTitulacion() {
		return idTitulacion;
	}


	public void setIdTitulacion(int idTitulacion) {
		this.idTitulacion = idTitulacion;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
    
    
	
}
