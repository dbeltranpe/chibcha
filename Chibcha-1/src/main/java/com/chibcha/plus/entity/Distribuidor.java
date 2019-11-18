package com.chibcha.plus.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Distribuidor 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty(message="El nombre del distribuidor es obligatorio")
	@Size(min = 5, max = 50, message="El nombre no debe sobrepasar los 50 caracteres")
	@Column
	private String nombre;
	
	@NotEmpty(message="El correo del distribuidor es obligatorio")
	@Size(min = 10, max = 400, message="El nombre no debe sobrepasar los 40 caracteres")
	@Column
	private String correo;
	
	@NotNull(message="La fecha de ingreso es obligatoria")
	@Column
	@DateTimeFormat (pattern="YYYY-MM-dd")
	private Date fechaingreso;

	public Long getId() 
	{
		return id;
	}

	public void setId(Long id) 
	{
		this.id = id;
	}

	public String getNombre() 
	{
		return nombre;
	}

	public void setNombre(String nombre) 
	{
		this.nombre = nombre;
	}

	public String getCorreo() 
	{
		return correo;
	}

	public void setCorreo(String correo) 
	{
		this.correo = correo;
	}

	public Date getFechaingreso() 
	{
		return fechaingreso;
	}

	public void setFechaingreso(Date fechaingreso) 
	{
		this.fechaingreso = fechaingreso;
	}
	
	
}
