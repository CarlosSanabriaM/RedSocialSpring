package com.uniovi.services;

import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.uniovi.entities.Post;
import com.uniovi.entities.User;

@Service
public class LoggerService {
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	public void newUserHasSignedUp(String email) {
		log.info("Un nuevo usuario se ha registrado con el siguiente email: {}", email);
	}
	
	public void userHasLoggedInFrom(String email, String urlLogin) {
		log.info("El usuario con email '{}' ha iniciado sesión desde {}",	email, urlLogin);
	}
	
	public void userHasAutoLoggedIn(String email) {
		log.info("El usuario con email '{}' ha iniciado sesión de forma automática después de registrarse", email);
	}
	
	public void errorByRoleInLogin(String email) {
		log.info("Intento de inicio de sesión fallido en el formulario de login, "
				+ "debido a que el rol del usuario que ha intentado entrar no es ROLE_PUBLIC. "
				+ "El email del usuario que ha intentado entrar desde /login es: {}", email);
	}
	
	public void errorByCredentialsInAdminLogin() {
		log.info("Intento de inicio de sesión fallido en el formulario de login del administrador "
				+ "(/admin/login), debido a credenciales erróneas.");
	}
	
	public void errorByRoleInAdminLogin(String email) {
		log.info("Intento de inicio de sesión fallido en el formulario de login del administrador, "
				+ "debido a que el rol del usuario que ha intentado entrar no es ROLE_ADMIN. "
				+ "El email del usuario que ha intentado entrar como administrador desde /admin/login es: {}", email);
	}

	public void userListSearchByEmailAndName(String email, String searchText, Page<User> users) {
		List<String> emails = new LinkedList<String>(); 
		users.getContent().forEach( (x) -> emails.add(x.getEmail()) );
		
		log.info("El usuario con email '{}' ha realizado un listado del resto de usuarios en base "
				+ "a una búsqueda por email o nombre con el siguiente texto: '{}'. "
				+ "El resultado de dicha búsqueda en la página {} "
				+ "(con número máximo de usuarios por página de {}) "
				+ "ha retornado los usuarios con los siguientes emails: {}", 
				email, searchText, users.getNumber(), users.getSize(), emails);
	}

	public void userList(String email, Page<User> users) {
		List<String> emails = new LinkedList<String>(); 
		users.getContent().forEach( (x) -> emails.add(x.getEmail()) );
		
		log.info("El usuario con email '{}' ha realizado un listado del resto de usuarios. "
				+ "El resultado de dicho listado en la página {} "
				+ "(con número máximo de usuarios por página de {}) "
				+ "ha retornado los usuarios con los siguientes emails: {}", 
				email, users.getNumber(), users.getSize(), emails);
	}

	public void userListUpdated(String email, Page<User> users) {
		List<String> emails = new LinkedList<String>(); 
		users.getContent().forEach( (x) -> emails.add(x.getEmail()) );
		
		log.info("El usuario con email '{}' ha realizado una actualización del listado del resto de usuarios. "
				+ "El resultado de dicho listado en la página {} "
				+ "(con número máximo de usuarios por página de {}) "
				+ "ha retornado los usuarios con los siguientes emails: {}", 
				email, users.getNumber(), users.getSize(), emails);
	}

	public void userDeleted(String email, Long id, String emailUserDeleted) {
		log.info("El usuario con email '{}' ha eliminado al usuario con los siguientes datos: "
				+ "[id: {} , email: {}]", 
				email, id, emailUserDeleted);
	}

	public void newPostCreated(Post post) {
		log.info("El usuario con email '{}' ha creado una nueva publicación con el siguiente contenido: "
				+ "[título: '{}', texto: '{}']", 
				post.getUser().getEmail(), post.getTitle(), 
				post.getText().trim().replaceAll("(\\r\\n|\\n|\\t| +)", " ") );
	}

	public void userListHisPosts(String email, List<Post> posts) {
		List<String> titles = new LinkedList<String>(); 
		posts.forEach( (x) -> titles.add(x.getTitle()) );
		
		log.info("El usuario con email '{}' ha listado sus publicaciones. "
				+ "Los títulos de dichas publicaciones son: {}", 
				email, titles);
	}
	
	public void databaseRestarted(String email) {
		log.info("El usuario con email '{}' ha reiniciado la base de datos. " + 
				"Se han eliminado todos los usuarios y se han vuelto a insertar los usuarios de prueba.", 
				email);
	}

	public void userNotExists(String email) {
		log.info("El usuario con email '{}' no existe.", 
				email);
	}
	
}
