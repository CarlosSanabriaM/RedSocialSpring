package com.uniovi.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uniovi.entities.Invitation;
import com.uniovi.entities.Post;
import com.uniovi.entities.User;
import com.uniovi.repositories.InvitationRepository;
import com.uniovi.repositories.UsersRepository;

@Service
public class InsertSampleDataService {
	
	@Autowired
	private UsersService usersService;
	
	@Autowired
	private RolesService rolesService;
	
	@Autowired
	private UsersRepository usersRepository;
	
	@Autowired
	private InvitationRepository invitationRepository;

	@SuppressWarnings("serial")
	@PostConstruct
	public void init() {
		
		// Usuarios normales
		
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
		
		
		Set<Post> user1Posts = new HashSet<Post>() {
			{
				add(new Post("Titulo A1", "Texto A1", new Date(1505777200000L), user1));
				add(new Post("Titulo A2", "Texto A2", new Date(1505133200000L), user1));
				add(new Post("Titulo A3", "Texto A3", new Date(1505999200000L), user1));
				add(new Post("Titulo A4", "Texto A4", new Date(1505888200000L), user1));				
			}
		};
		user1.setPosts(user1Posts);
		
		Set<Post> user2Posts = new HashSet<Post>() {
			{
				add(new Post("Titulo B1", "Texto B1", new Date(1505167200000L), user2));
				add(new Post("Titulo B2", "Texto B2", new Date(1333167207420L), user2));
				add(new Post("Titulo B3", "Texto B3", new Date(1698167200130L), user2));
				add(new Post("Titulo B4", "Texto B4", new Date(1235067200656L), user2));
			}
		};
		user2.setPosts(user2Posts);
		
		Set<Post> user3Posts = new HashSet<Post>() {
			{
				add(new Post("Titulo C1", "Texto C1", new Date(1504167200000L), user3));
				add(new Post("Titulo C2", "Texto C2", new Date(1325167207420L), user3));
				add(new Post("Titulo C3", "Texto C3", new Date(1105167200130L), user3));
				add(new Post("Titulo C4", "Texto C4", new Date(1507167200656L), user3));
			}
		};
		user3.setPosts(user3Posts);
		
		Set<Post> user4Posts = new HashSet<Post>() {
			{
				add(new Post("Titulo D1", "Texto D1", new Date(1504199200000L), user4));
				add(new Post("Titulo D2", "Texto D2", new Date(1525177207420L), user4));
				add(new Post("Titulo D3", "Texto D3", new Date(1105144200130L), user4));
				add(new Post("Titulo D4", "Texto D4", new Date(1507133200656L), user4));
			}
		};
		user4.setPosts(user4Posts);			
		
		
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
		
		user1.getFriends().add(user4);
		user1.getAuxFriends().add(user4);
		user4.getFriends().add(user1);
		user4.getAuxFriends().add(user1);
		
		user1.getFriends().add(user5);
		user1.getAuxFriends().add(user5);
		user5.getFriends().add(user1);
		user5.getAuxFriends().add(user1);
		
		user2.getFriends().add(user6);
		user2.getAuxFriends().add(user6);
		user6.getFriends().add(user2);
		user6.getAuxFriends().add(user2);
		
		usersRepository.save(user1);
		usersRepository.save(user2);
		usersRepository.save(user4);
		usersRepository.save(user5);
		usersRepository.save(user6);
		
		List<Invitation> invitaciones = new ArrayList<Invitation>() {
			{
				add(new Invitation(user1,user3));
				add(new Invitation(user7,user1));
				add(new Invitation(user8,user1));
				add(new Invitation(user9,user1));
				add(new Invitation(user10,user1));
			}
		};
		
		invitationRepository.save(invitaciones);
		
		
		// Administradores
		
		User admin = new User("admin@gmail.com", "NombreAdmin", "ApellidoAdmin");
		admin.setPassword("1234");
		admin.setRole(rolesService.getRoles()[1]);
		
		usersService.addUser(admin);

	}
	
	public void deleteAllAndInsertAgain() {
		invitationRepository.deleteAll();
		usersRepository.deleteAll();
		init();
	}
	
}