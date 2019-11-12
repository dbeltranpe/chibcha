package com.chibcha.plus.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.chibcha.plus.entity.Authority;

@Repository
public interface AuthorityRepository extends CrudRepository<Authority, Long> 
{
	public Authority findByAuthority(String authority);
	
}
