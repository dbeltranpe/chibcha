package com.chibcha.plus.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.chibcha.plus.entity.Cliente;
import com.chibcha.plus.entity.Usuario;

public interface ClienteRepository extends CrudRepository<Cliente, Long> 
{
	public Optional<Cliente> findByUsuario(Usuario usuario);

//	@Query(nativeQuery=true, value="select id_cliente, nom_cliente, id_tarjeta, id_plan, usuario_id from CLIENTE where usuario_id = ?1")
//	public Optional<Cliente> buscarConIdUsuario(Long idUsuario);

}
