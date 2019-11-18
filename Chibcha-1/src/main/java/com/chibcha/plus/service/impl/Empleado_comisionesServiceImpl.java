package com.chibcha.plus.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.chibcha.plus.commons.GenericServiceImpl;
import com.chibcha.plus.entity.Empleado_comisiones;
import com.chibcha.plus.repository.Empleado_comisionesRepository;
import com.chibcha.plus.service.api.Empleado_comisionesServiceAPI;

@Service
public class Empleado_comisionesServiceImpl extends GenericServiceImpl<Empleado_comisiones, Long> implements Empleado_comisionesServiceAPI 
{
	@Autowired
	private Empleado_comisionesRepository EmpComisionesDaoAPI;

	@Override
	public CrudRepository<Empleado_comisiones, Long> getDao() 
	{
		return EmpComisionesDaoAPI;
	}

}
