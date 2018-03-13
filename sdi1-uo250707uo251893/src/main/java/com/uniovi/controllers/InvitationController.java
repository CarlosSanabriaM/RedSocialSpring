package com.uniovi.controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.uniovi.entities.Invitation;
import com.uniovi.services.InvitationService;

@Controller
public class InvitationController {
	@Autowired
	private InvitationService invitationService;
	
	@RequestMapping("user/invitate/{id}")
	public String invitateUser( Principal principal, @PathVariable Long id ) {
		invitationService.sendInvitation(principal.getName(), id);		
		
		return "redirect:/user/list";
	}
	
	@RequestMapping("user/invitations")
	public String getInvitationes(Pageable pageable, Principal principal, Model model) {
		Page<Invitation> invitations = invitationService.getInvitationsOf(pageable, principal.getName());
		
		model.addAttribute("invitationsList", invitations.getContent());
		model.addAttribute("page", invitations);
		
		return "/user/invitations";
	}
	
	@RequestMapping("user/accept/{id}")
	public String acceptInvitation(Principal principal, @PathVariable Long id ) {
		invitationService.acceptInvitation(principal.getName(), id);
		
		return "redirect:/user/friends";
	}
	

}
