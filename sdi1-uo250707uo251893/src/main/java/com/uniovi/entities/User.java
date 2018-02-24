package com.uniovi.entities;

import java.util.Set;

import javax.persistence.*;

@Entity
public class User {
	@Id
	@GeneratedValue
	private long id;

	@Column(unique = true)
	private String email;
	private String name;
	private String lastName;
	private String password;
	
	@Transient
	private String passwordConfirm;
	private String role;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private Set<Post> posts;

	@Transient
	private Set<User> friends;//TODO - Corregir
	
	@Transient
	private Set<User> invitations;
	
	public User(String email, String name, String lastName) {
		this.email = email;
		this.name = name;
		this.lastName = lastName;
	}

	public User() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFullName() {
		return this.name + " " + this.lastName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordConfirm() {
		return passwordConfirm;
	}

	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Set<Post> getPosts() {
		return posts;
	}

	public void setPosts(Set<Post> posts) {
		this.posts = posts;
	}

	public Set<User> getFriends() {
		return friends;
	}

	public void setFriends(Set<User> friends) {
		this.friends = friends;
	}

	public Set<User> getInvitations() {
		return invitations;
	}

	public void setInvitations(Set<User> invitations) {
		this.invitations = invitations;
	}
	
}