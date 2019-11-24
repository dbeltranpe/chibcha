package com.chibcha.plus.entity.views;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface VENTAS_DISTRIBUIDOR_ANUAL_REPOSITORY extends CrudRepository<VENTAS_DISTRIBUIDOR_ANUAL, Long> 
{

	@Query(nativeQuery=true, value="select id, nombre, correo, valor, descripcion, fecha from VENTAS_DISTRIBUIDOR_ANUAL where nombre = ?1")
	public Iterable<VENTAS_DISTRIBUIDOR_ANUAL> findAllByNombre(String nombre);

}
