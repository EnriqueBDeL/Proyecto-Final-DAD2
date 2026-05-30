package edu.ucam.domain;

public class Asignatura {

    private String idAsignatura;
    private String nombre;
    private int maxCapacidad;
    private String idTitulacion;
    private String nombreTitulacion;
    private String idProfesor;
    private String nombreProfesor;

    public Asignatura(String idAsignatura, String nombre, int maxCapacidad, String idTitulacion, String nombreTitulacion, String idProfesor, String nombreProfesor) {
        this.idAsignatura = idAsignatura;
        this.nombre = nombre;
        this.maxCapacidad = maxCapacidad;
        this.idTitulacion = idTitulacion;
        this.nombreTitulacion = nombreTitulacion;
        this.idProfesor = idProfesor;
        this.nombreProfesor = nombreProfesor;
    }

    public String getIdAsignatura() { 
    	return idAsignatura; 
    }
    
    public void setIdAsignatura(String idAsignatura) { 
    	this.idAsignatura = idAsignatura; 
    }

    public String getNombre() { 
    	return nombre;
    }
    
    public void setNombre(String nombre) { 
    	this.nombre = nombre;
    }

    public int getMaxCapacidad() { 
    	return maxCapacidad;
    }
    
    public void setMaxCapacidad(int maxCapacidad) { 
    	this.maxCapacidad = maxCapacidad; 
    }

    public String getIdTitulacion() { 
    	return idTitulacion; 
    }
    
    public void setIdTitulacion(String idTitulacion) { 
    	this.idTitulacion = idTitulacion; 
    }

    public String getNombreTitulacion() { 
    	return nombreTitulacion; 
    }
    
    public void setNombreTitulacion(String nombreTitulacion) { 
    	this.nombreTitulacion = nombreTitulacion; 
    }

    public String getIdProfesor() { 
    	return idProfesor; 
    }
    
    public void setIdProfesor(String idProfesor) { 
    	this.idProfesor = idProfesor; 
    }

    public String getNombreProfesor() { 
    	return nombreProfesor; 
    }
    
    public void setNombreProfesor(String nombreProfesor) { 
    	this.nombreProfesor = nombreProfesor; 
    }
}