package com.pdcg.SistemaAdministrativo;

public class Vendedor {
	private int id;
	private String nombre;
	
	public Vendedor() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getNombreV() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String toString() {
	   return nombre;
   }
}
