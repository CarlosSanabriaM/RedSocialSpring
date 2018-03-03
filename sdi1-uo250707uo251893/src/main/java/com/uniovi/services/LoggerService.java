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
	
	public void errorByCredentialsInAdminLogin() {
		log.info("Intento de inicio de sesión fallido en el formulario de login del administrador, "
				+ "debido a credenciales erróneas.");
	}

	public void errorByRoleInAdminLogin(String email) {
		log.info("Intento de inicio de sesión fallido en el formulario de login del administrador, "
				+ "debido a que el rol del usuario que ha intentado entrar no es ROLE_ADMIN. "
				+ "El email del usuario que ha intentado entrar como administrador es: {}", email);
	}
	
}
