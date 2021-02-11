package com.exist.service;
import java.util.Optional;
import java.util.List;
import com.exist.model.Contact;
import com.exist.dao.ContactDao;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Service
@EnableJpaRepositories("com.exist.dao")
@ComponentScan(basePackages  = {"com.exist.dao"})
public class ContactService {
	
	private final ContactDao contactDao;
	
	@Autowired
	public ContactService(ContactDao contactDao) {
		this.contactDao = contactDao;
	}
	
	public List<Contact> getContacts() {
		return contactDao.findAll();
	}
	
	public void addContact(Contact contact) {
		Optional<Contact> contactOptional = contactDao.findContactByEmail(contact.getEmail());
		if(contactOptional.isPresent()) {
			throw new IllegalStateException("Email is already Exist!!");
		}
		contactDao.save(contact);
	}
	
	public void deleteContact(int contactId) {
		boolean exists = contactDao.existsById(contactId);
		if(!exists) {
			throw new IllegalStateException("Contact id " + contactId + " does not exists");
		}
		contactDao.deleteById(contactId);
	}
	
	public void editContact(Contact contact,int contactId) {
		boolean exists = contactDao.existsById(contactId);
		if(!exists) {
			throw new IllegalStateException("Contact id " + contactId + " does not exists");
		}
		contact.setContactId(contactId);
		contactDao.save(contact);
	}
}
