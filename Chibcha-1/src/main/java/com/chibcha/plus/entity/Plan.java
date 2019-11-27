package com.chibcha.plus.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Plan 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_plan")
	private Long id;
	
	@Column(name="nom_plan")
	private String nombre;
	
	@Column(name="num_dominios")
	private String numeroDominios;
	
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

	public String getNumeroDominios() 
	{
		return numeroDominios;
	}

	public void setNumeroDominios(String numeroDominios) 
	{
		this.numeroDominios = numeroDominios;
	}

	
}