package com.uniovi.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Invitation {
	@Id
	@GeneratedValue
	private long id;
	
	@ManyToOne
	private User receiver;
	
	@ManyToOne
	private User sender;
	
	public Invitation() {
		
	}
	
	public Invitation(User sender, User receiver) {
		this.sender = sender;
		this.receiver = receiver;
		
		sender.getSendInvitations().add(this);
		receiver.getReceivedInvitations().add(this);		
	}
	
	public void accept() {
		sender.getFriends().add(receiver);
		receiver.getFriends().add(sender);
		sender.getAuxFriends().add(receiver);
		receiver.getAuxFriends().add(sender);
	}
	
	public void unlink() {
		sender.getSendInvitations().remove(this);
		receiver.getReceivedInvitations().remove(this);
		
		receiver = null;
		sender = null;
	}

	public User getReceiver() {
		return receiver;
	}

	public void setReceiver(User receiver) {
		this.receiver = receiver;
	}

	public User getSender() {
		return sender;
	}

	public void setSender(User sender) {
		this.sender = sender;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((receiver == null) ? 0 : receiver.hashCode());
		result = prime * result + ((sender == null) ? 0 : sender.hashCode());
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
		Invitation other = (Invitation) obj;
		if (receiver == null) {
			if (other.receiver != null)
				return false;
		} else if (!receiver.equals(other.receiver))
			return false;
		if (sender == null) {
			if (other.sender != null)
				return false;
		} else if (!sender.equals(other.sender))
			return false;
		return true;
	}
	
	

}
