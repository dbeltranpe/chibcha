package com.chibcha.plus.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.constraints.NotEmpty;

@Entity
public class Cliente 
{
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_cliente")
	private Long id;
	
	@NotEmpty(message="Se debe indicar el nombre del cliente")
	@Size(min = 5, max = 50, message="El nombre no debe sobrepasar los 50 caracteres")
	@Column(name="nom_cliente")
	private String nombre;
	
	@NotEmpty(message="Se debe indicar la tarjeta del cliente")
	@Size(min = 13, max = 18, message="El número de la tarjeta de crédito debe tener entre 13 y 18 caracteres")
	@Column(name="tarjeta_cliente")
	private String tarjeta;

	@NotNull(message="Se debe indicar la suscripción")
	@Min(value=1, message="La suscripción debe ser 1,2,3 (Oro, Platino, Plata)")
	@Max(value=3, message="La suscripción debe ser 1,2,3 (Oro, Platino, Plata)")
	@Column(name="suscripcion_cliente")
	private int suscripcion;
	
	@Valid
	@JoinColumn(name="usuario_id",unique=true)
	@OneToOne(cascade = CascadeType.ALL)
	private Usuario usuario;
	
	public Cliente()
	{
		usuario = new Usuario();
		usuario.setEnabled(true);
	
	}

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

	public String getTarjeta() 
	{
		return tarjeta;
	}

	public void setTarjeta(String tarjeta) 
	{
		this.tarjeta = tarjeta;
	}

	public int getSuscripcion() 
	{
		return suscripcion;
	}

	public void setSuscripcion(int suscripcion)
	{
		this.suscripcion = suscripcion;
	}

	public Usuario getUsuario() 
	{
		return usuario;
	}

	public void setUsuario(Usuario usuario) 
	{
		this.usuario = usuario;
	}
	
	

}
