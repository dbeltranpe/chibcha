package com.chibcha.plus.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.chibcha.plus.commons.GenericServiceImpl;
import com.chibcha.plus.entity.Empleado_soporte;
import com.chibcha.plus.repository.Empleado_soporteRepository;
import com.chibcha.plus.service.api.Empleado_soporteServiceAPI;

@Service
public class Empleado_soporteServiceImpl extends GenericServiceImpl<Empleado_soporte, Long> implements Empleado_soporteServiceAPI
{
	@Autowired
	private Empleado_soporteRepository EmpSoporteDaoAPI;

	@Override
	public CrudRepository<Empleado_soporte, Long> getDao() 
	{
		return EmpSoporteDaoAPI;
	}

}
