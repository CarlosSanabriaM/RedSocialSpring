package com.uniovi.services;

import org.springframework.stereotype.Service;

@Service
public class RolesService {//TODO - Quizas haya que hacer una entidad Role y relacionarla con User (lo pone el pdf de la sesion 4)
	
	String[] roles = { "ROLE_STUDENT", "ROLE_PROFESSOR", "ROLE_ADMIN" };

	public String[] getRoles() {
		return roles;
	}
	
}