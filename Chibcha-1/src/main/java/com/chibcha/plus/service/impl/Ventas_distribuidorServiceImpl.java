package com.chibcha.plus.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.chibcha.plus.commons.GenericServiceImpl;
import com.chibcha.plus.entity.Ventas_distribuidor;
import com.chibcha.plus.repository.Ventas_distribuidorRepository;
import com.chibcha.plus.service.api.Ventas_distribuidorServiceAPI;

@Service
public class Ventas_distribuidorServiceImpl extends GenericServiceImpl<Ventas_distribuidor, Long> implements Ventas_distribuidorServiceAPI
{

	@Autowired
	private Ventas_distribuidorRepository Venta_distribuidorDaoAPI;

	@Override
	public CrudRepository<Ventas_distribuidor, Long> getDao() 
	{
		return Venta_distribuidorDaoAPI;
	}

}
