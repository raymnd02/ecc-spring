package com.exist.service;
import java.util.Optional;
import java.util.List;
import java.util.ArrayList;
import com.exist.model.Person;
import com.exist.model.Contact;
import com.exist.model.Role;
import com.exist.dao.PersonDao;
import java.util.stream.*;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Service
@EnableJpaRepositories("com.exist.dao.PersonDao")
@ComponentScan(basePackages  = {"com.exist.dao"})
public class PersonService {
	
	private final PersonDao personDao;
	
	@Autowired
	public PersonService(PersonDao personDao) {
		this.personDao = personDao;
	}
	
	public List<Person> getPersons() {
		List<Person> persons = new ArrayList<>();
		List<Person> nullRolePerson = personDao.findAll();
		nullRolePerson.stream()
			.forEach(nullperson -> {
				List<Role> roles = new ArrayList<>();
				nullperson.getRole().stream()
					.forEach(eachRole -> {
						Role role = new Role();
						role.setRoleId(eachRole.getRoleId());
						role.setRole(eachRole.getRole());
						roles.add(role);
					});
				nullperson.setRole(roles);
			});
		return nullRolePerson;
	}
	
	public void addPerson(Person person) {
		Optional<Person> personOptional = personDao.findPersonByFirstNameAndLastName(person.getFirstName(),person.getLastName());
		if(personOptional.isPresent()) {
			throw new IllegalStateException("Person is already exist!!");
		}
		personDao.save(person);
	}
	
	public void deletePerson(Long personId) {
		boolean exists = personDao.existsById(personId);
		if(!exists) {
			throw new IllegalStateException("Person id " + personId + " does not exists");
		}
		personDao.deleteById(personId);
	}
	
	public void editPerson(Person person,Long personId) {
		boolean exists = personDao.existsById(personId);
		if(!exists) {
			throw new IllegalStateException("Person id " + personId + " does not exists");
		}
		
		Optional<Person> personOptional = personDao.findPersonByFirstNameAndLastNameEdit(person.getFirstName(),person.getLastName(),personId);
		if(personOptional.isPresent()) {
			throw new IllegalStateException("Person is First and Last Nameame already exist!!");
		}
		Person existedPerson = personDao.findById(personId).get();
		person.setContact(existedPerson.getContact());
		person.setPersonId(personId);
		personDao.save(person);
	}
	
		public void addContactPerson(Contact contact,Long personId) {
		boolean exists = personDao.existsById(personId);
		if(!exists) {
			throw new IllegalStateException("Person id " + personId + " does not exists");
		}
		Person person = personDao.findById(personId).get();
		List<Contact> contacts = person.getContact();
		contacts.add(contact);
		person.setContact(contacts);
		personDao.save(person);
	}
}
