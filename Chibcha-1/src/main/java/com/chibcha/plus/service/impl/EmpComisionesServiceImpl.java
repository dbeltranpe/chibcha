package com.chibcha.plus.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;

import com.chibcha.plus.commons.GenericServiceImpl;
import com.chibcha.plus.entity.Empleado_comisiones;
import com.chibcha.plus.repository.EmpComisionesRepository;
import com.chibcha.plus.service.api.EmpComisionesServiceAPI;

public class EmpComisionesServiceImpl extends GenericServiceImpl<Empleado_comisiones, Long> implements EmpComisionesServiceAPI 
{
	@Autowired
	private EmpComisionesRepository EmpComisionesDaoAPI;

	@Override
	public CrudRepository<Empleado_comisiones, Long> getDao() 
	{
		return EmpComisionesDaoAPI;
	}

}
