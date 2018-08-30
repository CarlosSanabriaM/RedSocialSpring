package com.uniovi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uniovi.entities.Invitation;
import com.uniovi.entities.User;
import com.uniovi.repositories.InvitationRepository;
import com.uniovi.repositories.UsersRepository;

@Service
public class InvitationService {
	@Autowired
	private InvitationRepository invitationRepository;
	
	@Autowired
	private UsersRepository usersRepository;
	
	@Transactional
	public void sendInvitation(String sender_email, Long receiver_id) {
		User receiver = usersRepository.findOne(receiver_id);
		User sender = usersRepository.findByEmail(sender_email);
		
		if(receiver != null && sender.canInvite(receiver.getEmail())) {
			invitationRepository.save(new Invitation(sender, receiver));
		}
	}

	public Page<Invitation> getInvitationsOf(Pageable pageable, String email) {
		return invitationRepository.getInvitationsOf(pageable,email);
	}

	@Transactional
	public void acceptInvitation(String email, Long invitation_id) {
		Invitation invitation = invitationRepository.findOne(invitation_id);
		
		if(invitation != null && invitation.getReceiver().getEmail() == email) {
			invitation.accept();
			invitationRepository.delete(invitation);
		}
	}

}
