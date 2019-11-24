package com.chibcha.plus.entity.views;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="VENTAS_DISTRIBUIDOR_SEMESTRAL")
public class VENTAS_DISTRIBUIDOR_SEMESTRAL  
{
	@Column
	@Id
	private Long id;
	
	@Column
	private String nombre;
	
	@Column
	private String correo;
	
	@Column
	private int valor;
	
	@Column
	private String descripcion;
	
	@Column
	@DateTimeFormat (pattern="YYYY-MM-dd")
	private Date fecha;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public int getValor() {
		return valor;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

}
