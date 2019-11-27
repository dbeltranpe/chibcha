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
public class Dominio 
{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_dominio")
	private Long id;
	
	@Column(name="nom_dominio")
	private String nombre;
	
	@JoinColumn(name="id_cliente",unique=true)
	@OneToOne(cascade = CascadeType.ALL)
	private Cliente cliente;

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

	public Cliente getCliente() 
	{
		return cliente;
	}

	public void setCliente(Cliente cliente) 
	{
		this.cliente = cliente;
	}
	
	

}
