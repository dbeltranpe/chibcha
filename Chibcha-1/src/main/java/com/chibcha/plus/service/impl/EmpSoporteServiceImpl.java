package com.chibcha.plus.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;

import com.chibcha.plus.commons.GenericServiceImpl;
import com.chibcha.plus.entity.Empleado_soporte;
import com.chibcha.plus.repository.EmpSoporteRepository;
import com.chibcha.plus.service.api.EmpSoporteServiceAPI;

public class EmpSoporteServiceImpl extends GenericServiceImpl<Empleado_soporte, Long> implements EmpSoporteServiceAPI
{
	@Autowired
	private EmpSoporteRepository EmpSoporteDaoAPI;

	@Override
	public CrudRepository<Empleado_soporte, Long> getDao() 
	{
		return EmpSoporteDaoAPI;
	}

}
