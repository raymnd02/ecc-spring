package com.exist.ecc.app;
import java.util.List;


import com.exist.service.ContactService;
import com.exist.model.Contact;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.boot.autoconfigure.domain.EntityScan;

@RestController
@ComponentScan(basePackageClasses = {com.exist.service.ContactService.class})
@EntityScan("com.exist.model")
@RequestMapping(path = "api/v1/contact")
public class ContactResource {

	
	private  final ContactService contactService;
	
	@Autowired
	public ContactResource(ContactService contactService) {
		this.contactService = contactService;
	}
	
	@GetMapping
	public List<Contact> getContacts() {
		return contactService.getContacts();
	}

	@PostMapping
	public void addContact(@RequestBody Contact contact){
		contactService.addContact(contact);
	}
	
	@DeleteMapping(path = "{contactId}")
	public void deleteContact(@PathVariable("contactId") int contactId) {
		contactService.deleteContact(contactId);
	}
	
	@PostMapping(path = "edit/{contactId}")
	public void editContact(@RequestBody Contact contact,@PathVariable("contactId") int contactId){
		contactService.editContact(contact,contactId);
	}
}