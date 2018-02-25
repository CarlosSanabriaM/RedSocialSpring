package com.uniovi.services;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uniovi.entities.User;

@Service
public class InsertSampleDataService {
	
	@Autowired
	private UsersService usersService;
	
	@Autowired
	private RolesService rolesService;

	@PostConstruct
	public void init() {//TODO - Completar
		User user1 = new User("user1@gmail.com", "Pedro", "Díaz");
		user1.setPassword("1234");
		user1.setRole(rolesService.getRoles()[0]);
		
		User user2 = new User("user2@gmail.com", "Lucas", "Núñez");
		user2.setPassword("1234");
		user2.setRole(rolesService.getRoles()[0]);
		
		User user3 = new User("user3@gmail.com", "María", "Rodríguez");
		user3.setPassword("1234");
		user3.setRole(rolesService.getRoles()[0]);
		
		User user4 = new User("user4@gmail.com", "Marta", "Almonte");
		user4.setPassword("1234");
		user4.setRole(rolesService.getRoles()[0]);
		
		User user5 = new User("user5@gmail.com", "Pelayo", "Valdes");
		user5.setPassword("1234");
		user5.setRole(rolesService.getRoles()[0]);
		
		User user6 = new User("user6@gmail.com", "Edward", "Núñez");
		user6.setPassword("1234");
		user6.setRole(rolesService.getRoles()[0]);

		User user7 = new User("user7@gmail.com", "Nombre7", "Apellido7");
		user7.setPassword("1234");
		user7.setRole(rolesService.getRoles()[0]);
		
		User user8 = new User("user8@gmail.com", "Nombre8", "Apellido8");
		user8.setPassword("1234");
		user8.setRole(rolesService.getRoles()[0]);
		
		User user9 = new User("user9@gmail.com", "Nombre9", "Apellido9");
		user9.setPassword("1234");
		user9.setRole(rolesService.getRoles()[0]);
		
		User user10 = new User("user10@gmail.com", "Nombre10", "Apellido10");
		user10.setPassword("1234");
		user10.setRole(rolesService.getRoles()[0]);
		
		User user11 = new User("user11@gmail.com", "Nombre11", "Apellido11");
		user11.setPassword("1234");
		user11.setRole(rolesService.getRoles()[0]);
		
		User user12 = new User("user12@gmail.com", "Nombre12", "Apellido12");
		user12.setPassword("1234");
		user12.setRole(rolesService.getRoles()[0]);
		
		User user13 = new User("user13@gmail.com", "Nombre13", "Apellido13");
		user13.setPassword("1234");
		user13.setRole(rolesService.getRoles()[0]);
		
		User user14 = new User("user14@gmail.com", "Nombre14", "Apellido14");
		user14.setPassword("1234");
		user14.setRole(rolesService.getRoles()[0]);
		
		User user15 = new User("user15@gmail.com", "Nombre15", "Apellido15");
		user15.setPassword("1234");
		user15.setRole(rolesService.getRoles()[0]);
		
		User user16 = new User("user16@gmail.com", "Nombre16", "Apellido16");
		user16.setPassword("1234");
		user16.setRole(rolesService.getRoles()[0]);
		
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
		usersService.addUser(user2);
		usersService.addUser(user3);
		usersService.addUser(user4);
		usersService.addUser(user5);
		usersService.addUser(user6);
		usersService.addUser(user7);
		usersService.addUser(user8);
		usersService.addUser(user9);
		usersService.addUser(user10);
		usersService.addUser(user11);
		usersService.addUser(user12);
		usersService.addUser(user13);
		usersService.addUser(user14);
		usersService.addUser(user15);
		usersService.addUser(user16);

	}
	
}