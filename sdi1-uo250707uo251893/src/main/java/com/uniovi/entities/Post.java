package com.uniovi.entities;

import java.awt.Image;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Post {
	@Id
	@GeneratedValue
	private long id;
	private String title;
	private String text;
	@ManyToOne
	private User user;
	private Date date;
	private Image photo;
	
	public Post() {
		
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Image getPhoto() {
		return photo;
	}

	public void setPhoto(Image photo) {
		this.photo = photo;
	}
	
	
}
