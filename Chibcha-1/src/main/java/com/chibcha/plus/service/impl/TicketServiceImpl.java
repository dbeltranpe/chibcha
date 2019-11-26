package com.chibcha.plus.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.chibcha.plus.commons.GenericServiceImpl;
import com.chibcha.plus.entity.Ticket;
import com.chibcha.plus.repository.TicketRepository;
import com.chibcha.plus.service.api.TicketServiceAPI;

@Service
public class TicketServiceImpl extends GenericServiceImpl<Ticket, Long> implements TicketServiceAPI 
{

	@Autowired
	private TicketRepository ticketDaoAPI;
	
	@Override
	public CrudRepository<Ticket, Long> getDao() 
	{
		return ticketDaoAPI; 
	}

}
