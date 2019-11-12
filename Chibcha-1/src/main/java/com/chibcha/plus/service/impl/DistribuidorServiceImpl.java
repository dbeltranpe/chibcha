package com.chibcha.plus.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.chibcha.plus.commons.GenericServiceImpl;
import com.chibcha.plus.entity.Distribuidor;
import com.chibcha.plus.repository.DistribuidorRepository;
import com.chibcha.plus.service.api.DistribuidorServiceAPI;

@Service
public class DistribuidorServiceImpl extends GenericServiceImpl<Distribuidor, Long> implements DistribuidorServiceAPI 
{
	
	@Autowired
	private DistribuidorRepository distribuidorDaoAPI;
	
	@Override
	public CrudRepository<Distribuidor, Long> getDao() 
	{
		return distribuidorDaoAPI; 
	}

}
