package com.uniovi.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.uniovi.entities.Invitation;

public interface InvitationRepository extends CrudRepository<Invitation, Long> {

	@Query("SELECT i FROM Invitation i WHERE i.receiver.email = ?1")
	Page<Invitation> getInvitationsOf(Pageable pageable, String email);

}
