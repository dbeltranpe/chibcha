package com.chibcha.plus.entity.views;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface VENTAS_DISTRIBUIDOR_SEMESTRAL_REPOSITORY extends CrudRepository<VENTAS_DISTRIBUIDOR_SEMESTRAL, Long> 
{

	@Query(nativeQuery=true, value="select id, nombre, correo, valor, descripcion, fecha from VENTAS_DISTRIBUIDOR_SEMESTRAL where nombre = ?1")
	public Iterable<VENTAS_DISTRIBUIDOR_SEMESTRAL> findAllByNombre(String nombre);

}
