package com.chibcha.plus.repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.chibcha.plus.entity.Dominio;

public interface DominioRepository extends CrudRepository<Dominio, Long> 
{
	@Query(nativeQuery=true, value="SELECT id_dominio, nom_dominio, id_cliente FROM DOMINIO WHERE id_cliente = ?1")
	public Iterable<Dominio> listarPorCliente(Long idCliente);


}
