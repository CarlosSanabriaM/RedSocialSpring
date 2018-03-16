package com.uniovi.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Post {
	@Id
	@GeneratedValue
	private long id;
	private String title;
	private String text;
	private Boolean containsImage;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date date;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	
	public Post() {}
	
	public Post(String title, String text, Date date, User user) {
		this.title = title;
		this.text = text;
		this.date = date;
		this.user = user;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public Boolean getContainsImage() {
		return containsImage;
	}

	public void setContainsImage(Boolean containsImage) {
		this.containsImage = containsImage;
	}

//	public Image getPhoto() {
//		return photo;
//	}
//
//	public void setPhoto(Image photo) {
//		this.photo = photo;
//	}
	
	
		
}
