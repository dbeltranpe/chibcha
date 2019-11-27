package com.chibcha.plus.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.chibcha.plus.entity.Plan;

@Repository
public interface PlanRepository extends CrudRepository<Plan, Long> 
{
	
}
