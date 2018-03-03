package com.uniovi.services;

import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.uniovi.entities.User;

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
	
	public void errorByRoleInLogin(String email) {
		log.info("Intento de inicio de sesión fallido en el formulario de login, "
				+ "debido a que el rol del usuario que ha intentado entrar no es ROLE_PUBLIC. "
				+ "El email del usuario que ha intentado entrar desde /login es: {}", email);
	}
	
	public void errorByRoleInAdminLogin(String email) {
		log.info("Intento de inicio de sesión fallido en el formulario de login del administrador, "
				+ "debido a que el rol del usuario que ha intentado entrar no es ROLE_ADMIN. "
				+ "El email del usuario que ha intentado entrar como administrador es: {}", email);
	}

	public void userListSearchByEmailAndName(String email, String searchText, Page<User> users) {
		List<String> emails = new LinkedList<String>(); 
		users.getContent().forEach( (x) -> emails.add(x.getEmail()) );
		
		log.info("El usuario con email {} ha realizado un listado del resto de usuarios en base "
				+ "a una búsqueda por email o nombre con el siguiente texto: '{}'."
				+ "El resultado de dicha búsqueda ha retornado los usuarios con los siguientes emails: {}", 
				email, searchText, emails);
	}
	
}
