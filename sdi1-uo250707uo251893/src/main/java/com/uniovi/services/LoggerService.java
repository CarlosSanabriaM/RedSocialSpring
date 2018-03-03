package com.uniovi.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class LoggerService {
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	public void newUserHasSignedUp(String email) {
		log.info("Un nuevo usuario se ha registrado con el siguiente email: {}",	 email);
	}
	
}
