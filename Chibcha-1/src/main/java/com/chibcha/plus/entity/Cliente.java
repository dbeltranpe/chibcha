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
public class Cliente 
{
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_cliente")
	private Long id;
	
	@Column(name="nom_cliente")
	private String nombre;
	
	@Column(name="tarjeta_cliente")
	private String tarjeta;

	@Column(name="suscripcion_cliente")
	private int suscripcion;
	
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
