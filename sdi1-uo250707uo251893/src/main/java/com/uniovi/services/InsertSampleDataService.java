package com.uniovi.services;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.error.Mark;

import com.uniovi.entities.User;

@Service
public class InsertSampleDataService {
	
	@Autowired
	private UsersService usersService;
	
	@Autowired
	private RolesService rolesService;

	@SuppressWarnings({ "rawtypes", "serial", "unchecked" })
	@PostConstruct
	public void init() {//TODO - Completar
		User user1 = new User("user1@gmail.com", "Pedro", "Díaz");
		user1.setPassword("123456");
		user1.setRole(rolesService.getRoles()[0]);
		
//		User user2 = new User("99999991B", "Lucas", "Núñez");
//		user2.setPassword("123456");
//		user2.setRole(rolesService.getRoles()[0]);
//		
//		User user3 = new User("99999992C", "María", "Rodríguez");
//		user3.setPassword("123456");
//		user3.setRole(rolesService.getRoles()[0]);
//		
//		User user4 = new User("99999993D", "Marta", "Almonte");
//		user4.setPassword("123456");
//		user4.setRole(rolesService.getRoles()[1]);
//		
//		User user5 = new User("99999977E", "Pelayo", "Valdes");
//		user5.setPassword("123456");
//		user5.setRole(rolesService.getRoles()[1]);
//		
//		User user6 = new User("99999988F", "Edward", "Núñez");
//		user6.setPassword("123456");
//		user6.setRole(rolesService.getRoles()[2]);
//		
//		Set user1Marks = new HashSet<Mark>() {
//			{
//				add(new Mark("Nota A1", 10.0, user1));
//				add(new Mark("Nota A2", 9.0, user1));
//				add(new Mark("Nota A3", 7.0, user1));
//				add(new Mark("Nota A4", 6.5, user1));
//			}
//		};
//		user1.setMarks(user1Marks);
//		
//		Set user2Marks = new HashSet<Mark>() {
//			{
//				add(new Mark("Nota B1", 5.0, user2));
//				add(new Mark("Nota B2", 4.3, user2));
//				add(new Mark("Nota B3", 8.0, user2));
//				add(new Mark("Nota B4", 3.5, user2));
//			}
//		};
//		user2.setMarks(user2Marks);
//		
//		Set user3Marks = new HashSet<Mark>() {
//			{
//				;
//				add(new Mark("Nota C1", 5.5, user3));
//				add(new Mark("Nota C2", 6.6, user3));
//				add(new Mark("Nota C3", 7.0, user3));
//			}
//		};
//		user3.setMarks(user3Marks);
//		
//		Set user4Marks = new HashSet<Mark>() {
//			{
//				add(new Mark("Nota D1", 10.0, user4));
//				add(new Mark("Nota D2", 8.0, user4));
//				add(new Mark("Nota D3", 9.0, user4));
//			}
//		};
//		user4.setMarks(user4Marks);
//		
		usersService.addUser(user1);
//		usersService.addUser(user2);
//		usersService.addUser(user3);
//		usersService.addUser(user4);
//		usersService.addUser(user5);
//		usersService.addUser(user6);
//	
	}
	
}