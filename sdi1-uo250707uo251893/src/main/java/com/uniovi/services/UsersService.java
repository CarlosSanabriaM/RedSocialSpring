package com.uniovi.services;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.uniovi.entities.User;
import com.uniovi.repositories.UsersRepository;

@Service
public class UsersService {
	
	@Autowired
	private UsersRepository usersRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@PostConstruct
	public void init() {}

	/**
	 * Devuelve los usuarios que tienen ROLE_PUBLIC
	 * @param pageable
	 * @return
	 */
	public List<User> getUsers() {
		List<User> users = new ArrayList<User>();
		usersRepository.findAllByRole("ROLE_PUBLIC").forEach(users::add);
		return users;
	}
	
	/**
	 * Devuelve los usuarios que tienen ROLE_PUBLIC
	 * @param pageable
	 * @return
	 */
	public Page<User> getUsers(Pageable pageable) {
		System.out.println(getUsers());
		return usersRepository.findAllByRole(pageable, "ROLE_PUBLIC");
	}

	public User getUser(Long id) {
		return usersRepository.findOne(id);
	}

	public void addUser(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		usersRepository.save(user);
	}

	public void deleteUser(Long id) {
		usersRepository.delete(id);
	}
	
	public User getUserByEmail(String email) {
		return usersRepository.findByEmail(email);
	}
	
	public Page<User> searchUsersByEmailAndName(Pageable pageable, String searchText) {
		searchText = "%" + searchText + "%"; // Comodines para el SQL
		return usersRepository.searchByEmailAndNameByRole(pageable, searchText, "ROLE_PUBLIC");
	}
	
}
