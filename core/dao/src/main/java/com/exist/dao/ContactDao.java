package com.exist.dao;
import java.util.Optional;
import com.exist.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactDao extends JpaRepository<Contact, Integer>{

	@Query("Select c From Contact c Where c.email = ?1" )
	Optional<Contact> findContactByEmail(String email);
	
}
