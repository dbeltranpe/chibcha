package com.chibcha.plus.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Tarjeta 
{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_tarjeta")
	private Long id;
	
	@Column
	private String titular;
	
	@Column(name="f_vencimiento")
	private String fechaVencimiento;
	
	@Column(name="num_seguridad")
	private int numSeguridad;
	
	@Column(name="num_tarjeta")
	private String numTarjeta;

	public String getNumTarjeta() {
		return numTarjeta;
	}

	public void setNumTarjeta(String numTarjeta) {
		this.numTarjeta = numTarjeta;
	}

	public Long getId() 
	{
		return id;
	}

	public void setId(Long id) 
	{
		this.id = id;
	}

	public String getTitular() 
	{
		return titular;
	}

	public void setTitular(String titular) 
	{
		this.titular = titular;
	}

	public String getFechaVencimiento() 
	{
		return fechaVencimiento;
	}

	public void setFechaVencimiento(String fechaVencimiento)
	{
		this.fechaVencimiento = fechaVencimiento;
	}

	public int getNumSeguridad() 
	{
		return numSeguridad;
	}

	public void setNumSeguridad(int numSeguridad) 
	{
		this.numSeguridad = numSeguridad;
	}
	

}
