package com.chibcha.plus.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.chibcha.plus.entity.Ticket;

public interface TicketRepository extends CrudRepository<Ticket, Long> 
{
	
	@Query(nativeQuery=true, value="SELECT id_ticket, correo, problema, id_estado, id_nivel_servicio FROM TICKET WHERE id_nivel_servicio=0;")
	public Iterable<Ticket> listarSinClasificar();
	
	@Query(nativeQuery=true, value="SELECT id_ticket, correo, problema, id_estado, id_nivel_servicio FROM TICKET WHERE id_nivel_servicio=1 AND id_estado=1;")
	public Iterable<Ticket> listarUrgenteSinAtender();

	@Query(nativeQuery=true, value="SELECT id_ticket, correo, problema, id_estado, id_nivel_servicio FROM TICKET WHERE id_nivel_servicio=1 AND id_estado=2;")
	public Iterable<Ticket> listarUrgenteEnAtencion();
	
	@Query(nativeQuery=true, value="SELECT id_ticket, correo, problema, id_estado, id_nivel_servicio FROM TICKET WHERE id_nivel_servicio=1 AND id_estado=3;")
	public Iterable<Ticket> listarUrgenteAtendido();
	
	@Query(nativeQuery=true, value="SELECT id_ticket, correo, problema, id_estado, id_nivel_servicio FROM TICKET WHERE id_nivel_servicio=2 AND id_estado=1;")
	public Iterable<Ticket> listarImportanteSinAtender();

	@Query(nativeQuery=true, value="SELECT id_ticket, correo, problema, id_estado, id_nivel_servicio FROM TICKET WHERE id_nivel_servicio=2 AND id_estado=2;")
	public Iterable<Ticket> listarImportanteEnAtencion();
	
	@Query(nativeQuery=true, value="SELECT id_ticket, correo, problema, id_estado, id_nivel_servicio FROM TICKET WHERE id_nivel_servicio=2 AND id_estado=3;")
	public Iterable<Ticket> listarImportanteAtendido();
	
	@Query(nativeQuery=true, value="SELECT id_ticket, correo, problema, id_estado, id_nivel_servicio FROM TICKET WHERE id_nivel_servicio=3 AND id_estado=1;")
	public Iterable<Ticket> listarNormalSinAtender();

	@Query(nativeQuery=true, value="SELECT id_ticket, correo, problema, id_estado, id_nivel_servicio FROM TICKET WHERE id_nivel_servicio=3 AND id_estado=2;")
	public Iterable<Ticket> listarNormalEnAtencion();
	
	@Query(nativeQuery=true, value="SELECT id_ticket, correo, problema, id_estado, id_nivel_servicio FROM TICKET WHERE id_nivel_servicio=3 AND id_estado=3;")
	public Iterable<Ticket> listarNormalAtendido();


}
