package com.uce.edu.demo.repository.modelo;

public class ProductoTO {
	

	private String codigoBarras;
	private Integer cantidad;
	
	public ProductoTO() {
		
	}
	
	public ProductoTO(String codigoBarras,Integer cantidad) {
		super();
		this.codigoBarras=codigoBarras;
		this.cantidad=cantidad;
	}

	public String getCodigoBarras() {
		return codigoBarras;
	}

	public void setCodigoBarras(String codigoBarras) {
		this.codigoBarras = codigoBarras;
	}

	public Integer getStock() {
		return cantidad;
	}

	public void setStock(Integer stock) {
		this.cantidad = stock;
	}

	@Override
	public String toString() {
		return "ProductoTO [codigoBarras=" + codigoBarras + ", cantidad=" + cantidad + "]";
	}

	
	
	
	
}
