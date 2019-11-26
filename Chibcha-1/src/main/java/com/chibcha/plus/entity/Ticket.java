package com.chibcha.plus.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

@Entity
public class Ticket 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_ticket")
	private Long id;
	
	@Column
	@NotEmpty(message="Se debe escribir un correo para hacer la solicitud")
	private String correo;
	
	@Column
	@NotEmpty(message="Se debe específicar el problema")
	@Length(min=10, max=499, message="La longitud del problema debe estar entre los 10 y 450 carácteres")
	private String problema;
	
//	@NotNull(message="Se debe seleccionar un estado")
	@JoinColumn(name="id_estado",unique=true, insertable=false)
	@OneToOne(cascade = CascadeType.ALL)
	private Estado estado;
	
//	@NotNull(message="Se debe seleccionar un nivel de servicio")
	@JoinColumn(name="id_nivel_servicio",unique=true, insertable=false)
	@OneToOne(cascade = CascadeType.ALL)
	private Nivel_servicio nivelServicio;

	public Long getId() 
	{
		return id;
	}

	public void setId(Long id) 
	{
		this.id = id;
	}

	public String getCorreo() 
	{
		return correo;
	}

	public void setCorreo(String correo) 
	{
		this.correo = correo;
	}

	public String getProblema() 
	{
		return problema;
	}

	public void setProblema(String problema) 
	{
		this.problema = problema;
	}

	public Estado getEstado() 
	{
		return estado;
	}

	public void setEstado(Estado estado) 
	{
		this.estado = estado;
	}

	public Nivel_servicio getNivelServicio() 
	{
		return nivelServicio;
	}

	public void setNivelServicio(Nivel_servicio nivelServicio) 
	{
		this.nivelServicio = nivelServicio;
	}


	

}
