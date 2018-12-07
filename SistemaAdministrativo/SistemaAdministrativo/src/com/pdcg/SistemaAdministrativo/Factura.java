package com.pdcg.SistemaAdministrativo;

public class Factura {
	public int id;
	

	public int subtotal;
	public int numfactura;
	public String fefactura;
	public int total;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(int subtotal) {
		this.subtotal = subtotal;
	}

	public int getNumfactura() {
		return numfactura;
	}

	public void setNumfactura(int numfactura) {
		this.numfactura = numfactura;
	}

	public String getFefactura() {
		return fefactura;
	}

	public void setFefactura(String fefactura) {
		this.fefactura = fefactura;
	}

	
	
	
}
