/*
 * Created on Feb 24, 2012
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and 

Comments
 */
package com.bituos.bar.Beans;

import java.util.Date;



/**
 * @author dsa
*/

public class BeanProducto{

	private int id;
	private String descripcion;
	private Integer cantidad_minima;
	private String codigo_barras;
	private String material;
	private String color;
	private Double precio_costo;
	private byte[] foto_frente;
	private byte[] foto_atras;
	private byte[] foto_lado;
	private byte[] foto_lado1;
	private byte[] foto_abajo;
	private byte[] foto_arriba;
	private Double precio_venta;
	private char aplica_descuento;
	private char venta_publico;
	private BeanMoneda id_moneda;
	private char controlar_existencia;
	private Integer id_tipo;
	private Double ganancia;
	private Integer cantidad_maxima;
	private char eliminado;
	private char invisible;
	private char venta_web;
	private BeanMarca id_marca;
	private Double impuesto;
	private Double cantidad;
	private Integer id_almacen;
	private BeanCategoria id_categoria;
	
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public BeanCategoria getId_categoria() {
		return id_categoria;
	}
	public void setId_categoria(BeanCategoria id_categoria) {
		this.id_categoria = id_categoria;
	}
	public BeanMoneda getId_moneda() {
		return id_moneda;
	}
	public void setId_moneda(BeanMoneda id_moneda) {
		this.id_moneda = id_moneda;
	}
	public Integer getCantidad_minima() {
		return cantidad_minima;
	}
	public void setCantidad_minima(Integer cantidad_minima) {
		this.cantidad_minima = cantidad_minima;
	}
	public String getCodigo_barras() {
		return codigo_barras;
	}
	public void setCodigo_barras(String codigo_barras) {
		this.codigo_barras = codigo_barras;
	}
	public String getMaterial() {
		return material;
	}
	public void setMaterial(String material) {
		this.material = material;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public Double getPrecio_costo() {
		return precio_costo;
	}
	public void setPrecio_costo(Double precio_costo) {
		this.precio_costo = precio_costo;
	}
	public byte[] getFoto_frente() {
		return foto_frente;
	}
	public void setFoto_frente(byte[] foto_frente) {
		this.foto_frente = foto_frente;
	}
	public byte[] getFoto_atras() {
		return foto_atras;
	}
	public void setFoto_atras(byte[] foto_atras) {
		this.foto_atras = foto_atras;
	}
	public byte[] getFoto_lado() {
		return foto_lado;
	}
	public void setFoto_lado(byte[] foto_lado) {
		this.foto_lado = foto_lado;
	}
	public byte[] getFoto_lado1() {
		return foto_lado1;
	}
	public void setFoto_lado1(byte[] foto_lado1) {
		this.foto_lado1 = foto_lado1;
	}
	public byte[] getFoto_abajo() {
		return foto_abajo;
	}
	public void setFoto_abajo(byte[] foto_abajo) {
		this.foto_abajo = foto_abajo;
	}
	public byte[] getFoto_arriba() {
		return foto_arriba;
	}
	public void setFoto_arriba(byte[] foto_arriba) {
		this.foto_arriba = foto_arriba;
	}
	public Double getPrecio_venta() {
		return precio_venta;
	}
	public void setPrecio_venta(Double precio_venta) {
		this.precio_venta = precio_venta;
	}
	public char getAplica_descuento() {
		return aplica_descuento;
	}
	public void setAplica_descuento(char aplica_descuento) {
		this.aplica_descuento = aplica_descuento;
	}
	public char getVenta_publico() {
		return venta_publico;
	}
	public void setVenta_publico(char venta_publico) {
		this.venta_publico = venta_publico;
	}
	public char getControlar_existencia() {
		return controlar_existencia;
	}
	public void setControlar_existencia(char controlar_existencia) {
		this.controlar_existencia = controlar_existencia;
	}
	public Integer getId_tipo() {
		return id_tipo;
	}
	public void setId_tipo(Integer id_tipo) {
		this.id_tipo = id_tipo;
	}
	public Double getGanancia() {
		return ganancia;
	}
	public void setGanancia(Double ganancia) {
		this.ganancia = ganancia;
	}

	
	
	
	public Integer getCantidad_maxima() {
		return cantidad_maxima;
	}
	public void setCantidad_maxima(Integer cantidad_maxima) {
		this.cantidad_maxima = cantidad_maxima;
	}
	public char getEliminado() {
		return eliminado;
	}
	public void setEliminado(char eliminado) {
		this.eliminado = eliminado;
	}
	public char getInvisible() {
		return invisible;
	}
	public void setInvisible(char invisible) {
		this.invisible = invisible;
	}
	public char getVenta_web() {
		return venta_web;
	}
	public void setVenta_web(char venta_web) {
		this.venta_web = venta_web;
	}
	public BeanMarca getId_marca() {
		return id_marca;
	}
	public void setId_marca(BeanMarca id_marca) {
		this.id_marca = id_marca;
	}
	public Double getImpuesto() {
		return impuesto;
	}
	public void setImpuesto(Double impuesto) {
		this.impuesto = impuesto;
	}
	public Double getCantidad() {
		return cantidad;
	}
	public void setCantidad(Double cantidad) {
		this.cantidad = cantidad;
	}
	public Integer getId_almacen() {
		return id_almacen;
	}
	public void setId_almacen(Integer id_almacen) {
		this.id_almacen = id_almacen;
	}
	


	

	
}
