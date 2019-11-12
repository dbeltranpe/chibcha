package com.chibcha.plus.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.chibcha.plus.commons.GenericServiceImpl;
import com.chibcha.plus.entity.Cliente;
import com.chibcha.plus.repository.ClienteRepository;
import com.chibcha.plus.service.api.ClienteServiceAPI;

@Service
public class ClienteServiceImpl extends GenericServiceImpl<Cliente, Long> implements ClienteServiceAPI  
{
	
	@Autowired
	private ClienteRepository ClienteDaoAPI;

	@Override
	public CrudRepository<Cliente, Long> getDao() 
	{
		return ClienteDaoAPI;
	}

}
