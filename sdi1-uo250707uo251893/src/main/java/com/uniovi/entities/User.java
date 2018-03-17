package com.uniovi.entities;

import java.util.HashSet;
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
	private Set<Post> posts = new HashSet<Post>();

	@ManyToMany(cascade = {CascadeType.PERSIST , CascadeType.REFRESH})
	@JoinTable(name="friends",
			joinColumns= {@JoinColumn(name="friend_id")},
			inverseJoinColumns = {@JoinColumn(name="aux_friend_id")})
	private Set<User> friends = new HashSet<User>();
	
	@ManyToMany(mappedBy="friends")
	private Set<User> auxFriends = new HashSet<User>();

	@OneToMany(mappedBy = "receiver", cascade = CascadeType.ALL)
	private Set<Invitation> receivedInvitations = new HashSet<Invitation>();
	@OneToMany(mappedBy = "sender", cascade = CascadeType.ALL)
	private Set<Invitation> sendedInvitations = new HashSet<Invitation>();

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

	public Set<Invitation> getReceivedInvitations() {
		return receivedInvitations;
	}

	public void setReceivedInvitations(Set<Invitation> receivedInvitations) {
		this.receivedInvitations = receivedInvitations;
	}

	public Set<Invitation> getSendInvitations() {
		return sendedInvitations;
	}

	public void setSendInvitations(Set<Invitation> sendInvitations) {
		this.sendedInvitations = sendInvitations;
	}

	public Set<User> getAuxFriends() {
		return auxFriends;
	}

	public void setAuxFriends(Set<User> auxFriends) {
		this.auxFriends = auxFriends;
	}

	public Set<Invitation> getSendedInvitations() {
		return sendedInvitations;
	}

	public void setSendedInvitations(Set<Invitation> sendedInvitations) {
		this.sendedInvitations = sendedInvitations;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", email=" + email + ", name=" + name + ", lastName=" + lastName + ", role=" + role
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		return true;
	}

	public void borrarAmigos() {
		User[] amigos = new User[friends.size()];
		friends.toArray(amigos);
		
		for(User amigo : amigos) {
			this.friends.remove(amigo);
			this.auxFriends.remove(amigo);
			amigo.friends.remove(this);
			amigo.auxFriends.remove(this);
		}
	}
	
	

}