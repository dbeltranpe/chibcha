/**
 * 
 */
package com.chibcha.plus.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Passgenerator 
{
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public Passgenerator()
	{
		bCryptPasswordEncoder = new BCryptPasswordEncoder(4);
		
	}
	
	public String cifrar(String pTexto)
	{
		return bCryptPasswordEncoder.encode(pTexto);
	}

}