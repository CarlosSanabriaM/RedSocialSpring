package com.uniovi.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.uniovi.entities.User;

public interface UsersRepository extends CrudRepository<User, Long> {

	User findByEmail(String email);
	
	Page<User> findAll(Pageable pageable); 
	
	List<User> findAllByRole(String role);
	
	@Query("SELECT u FROM User u WHERE u.role = ?1")
	Page<User> findAllByRole(Pageable pageable, String role);
	
	@Query("SELECT u FROM User u WHERE u.role = ?2 AND "
			+ "(LOWER(u.name + ' ' + u.lastName) LIKE LOWER(?1) OR LOWER(u.email) LIKE LOWER(?1))")
	Page<User> searchByEmailAndNameByRole(Pageable pageable, String seachtext, String role);

	void deleteAll();

	@Query("SELECT u FROM User u JOIN u.friends f WHERE f.email = ?1")
	Page<User> getFriendsOf(Pageable pageable, String email);
	
}