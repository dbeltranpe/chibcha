package com.chibcha.plus.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Empleado_comisiones
{
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_emp_comisiones")
	private Long id;
	
	@Column(name="nom_emp_comisiones")
	private String nombre;
	
	@Column(name="sueldo_emp_comisiones")
	private int sueldo;
	
	@JoinColumn(name="usuario_id",unique=true)
	@OneToOne(cascade = CascadeType.ALL)
	private Usuario usuario;
	
	public Empleado_comisiones()
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
