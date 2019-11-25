package com.chibcha.plus.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="VENTAS_DISTRIBUIDOR")
public class Ventas_distribuidor 
{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_venta_distribuidor")
	private Long idVenta;
	
	@NotNull(message="Se debe indicar el valor de la comisión")
	@Column(name="valor_venta_distribuidor")
	private int valor;
	
	@NotEmpty(message="La descripción es obligatorio")
	@Column(name="descripcion_venta_distribuidor")
	private String descripcion;
	
	@NotNull(message="Se debe seleccionar un distribuidor")
	@JoinColumn(name="id_distribuidor",unique=true)
	@OneToOne(cascade = CascadeType.ALL)
	private Distribuidor distribuidor;
	
	@NotNull(message="La fecha de la comisión es obligatoria")
	@Column
	@DateTimeFormat (pattern="yyyy-MM-dd")
	private Date fecha;

	public Long getIdVenta() 
	{
		return idVenta;
	}

	public void setIdVenta(Long idVenta) 
	{
		this.idVenta = idVenta;
	}

	public int getValor() 
	{
		return valor;
	}

	public void setValor(int valor) 
	{
		this.valor = valor;
	}

	public String getDescripcion() 
	{
		return descripcion;
	}

	public void setDescripcion(String descripcion) 
	{
		this.descripcion = descripcion;
	}

	public Distribuidor getDistribuidor() 
	{
		return distribuidor;
	}

	public void setDistribuidor(Distribuidor distribuidor) 
	{
		this.distribuidor = distribuidor;
	}

	public Date getFecha()
	{
		return fecha;
	}

	public void setFecha(Date fecha) 
	{
		this.fecha = fecha;
	}
	

}
