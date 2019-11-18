package com.chibcha.plus.entity;

import javax.persistence.Entity;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class Empleado 
{
	private Long id;
	
	@NotEmpty(message="Se debe indicar el nombre del empleado")
	private String nombre;
	
	@NotNull(message="Se debe indicar el sueldo del empleado")
	private int sueldo;
	
	@Valid
	private Usuario usuario;
	
	public Empleado()
	{
		usuario = new Usuario();
		usuario.setEnabled(true);
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getSueldo() {
		return sueldo;
	}

	public void setSueldo(int sueldo) {
		this.sueldo = sueldo;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
