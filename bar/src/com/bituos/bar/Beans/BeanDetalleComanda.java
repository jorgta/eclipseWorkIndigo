/*
 * Created on Feb 24, 2012
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and 

Comments
 */
package com.bituos.bar.Beans;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;
import java.io.Serializable;



/**
 * @author dsa
*/

public class BeanDetalleComanda implements Serializable{


	private BeanComandas id_comanda;
	private int cantidad;
	private Double precio_unit;
	private char printed;
	private String producto_descripcion;
    //private BeanComandas id_comanda;
	private Date  date_capture;
	private String note;
	private BeanProducto id_producto;
	
	
	 
	  
	  
	@Override
    public boolean equals(Object o) {
        if(o instanceof BeanComandas){
        	BeanComandas other = (BeanComandas) o;
            return id_comanda.equals(other.getId_comanda());
        }

        return false;
    }
	
	@Override
    public int hashCode() {
        return id_comanda.hashCode();
    }
	
	
	

	
	public BeanComandas getId_comanda() {
		return id_comanda;
	}
	public void setId_comanda(BeanComandas id_comanda) {
		this.id_comanda = id_comanda;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public Double getPrecio_unit() {
		return precio_unit;
	}
	public void setPrecio_unit(Double precio_unit) {
		this.precio_unit = precio_unit;
	}
	public char getPrinted() {
		return printed;
	}
	public void setPrinted(char printed) {
		this.printed = printed;
	}
	public String getProducto_descripcion() {
		return producto_descripcion;
	}
	public void setProducto_descripcion(String producto_descripcion) {
		this.producto_descripcion = producto_descripcion;
	}

	

	
	public Date getDate_capture() {
		return date_capture;
	}
	public void setDate_capture(Date date_capture) {
		this.date_capture = date_capture;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public BeanProducto getId_producto() {
		return id_producto;
	}
	public void setId_producto(BeanProducto id_producto) {
		this.id_producto = id_producto;
	}
	
	
	
	
}
