package com.exist.service;
import java.util.Optional;
import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Collections;
import com.exist.model.Person;
import com.exist.model.Contact;
import com.exist.model.Role;
import com.exist.utils.Utils;
import com.exist.dao.PersonDao;
import java.util.stream.*;
import java.util.Iterator;
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
		List<Person> returnPerson = personDao.findAll();
		return Utils.getFixList(returnPerson);
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
		List<Role> role = new ArrayList<>();
		if(!exists) {
			throw new IllegalStateException("Person id " + personId + " does not exists");
		}
		Optional<Person> personOptional = personDao.findById(personId);
		personOptional.get().setRole(role);
		personDao.save(personOptional.get());
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
		person.setRole(existedPerson.getRole());
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
	
	public void addPersonRole(Long personId,Role personRole){
		boolean roleExist = false;
		Optional<Person> personOptional = personDao.findById(personId);
		if(!personOptional.isPresent()) {
			throw new IllegalStateException("Person id " + personId + " does not exists");
		}
		
		List<Role> roles = personOptional.get().getRole(); 
		for(Role role : roles) {
			roleExist = (role.getRoleId() == personRole.getRoleId() ? true : false);
		}
		if(roleExist) {
			throw new IllegalStateException("Person has existing roles!!");
		}
		roles.add(personRole);
		Person person = personOptional.get();
		person.setRole(roles);
		personDao.save(person);
	}
	
	public void deleteRoleConstraints(Role role) {
		List<Person> persons = personDao.findAll();
		persons.stream()
			.forEach(person -> {
				
				Iterator<Role> itr = person.getRole().iterator();
				while (itr.hasNext()) { 
					Role roleToBeDeleted = itr.next(); 
					if (roleToBeDeleted.getRoleId() == role.getRoleId()) { 
						itr.remove(); 
					} 
				}
				personDao.save(person);
			});
	}
	public void deletePersonRole(Long personId,int roleId) {
		boolean roleExist = false;
		Optional<Person> personOptional = personDao.findById(personId);
		if(!personOptional.isPresent()) {
			throw new IllegalStateException("Person id " + personId + " does not exists");
		}
		
		List<Role> roles = personOptional.get().getRole(); 
		for(Role role : roles) {
			roleExist = (role.getRoleId() == roleId ? true : false);
		}
		if(!roleExist) {
			throw new IllegalStateException("Person has doesn't have roles with " + roleId + " id!!");
		}
		Iterator<Role> itr = roles.iterator();
		while (itr.hasNext()) { 
			Role roleToBeDeleted = itr.next(); 
			if (roleToBeDeleted.getRoleId() == roleId) { 
				itr.remove(); 
			} 
		}
		
		Person person = personOptional.get();
		person.setRole(roles);
		personDao.save(person);
	}
	
	public List<Person> getPersonsOrderByLastName() {
		Optional<List<Person>> orderByLastName = personDao.findPersonOrderByLastName();
		if(!orderByLastName.isPresent()) {
			throw new IllegalStateException("No Records Found!!");
		}
		
		
		return Utils.getFixList(orderByLastName.get());
	}
	
	public List<Person> getPersonsOrderByDateHired() {
		Optional<List<Person>> orderByDateHired = personDao.findPersonOrderByDateHired();
		if(!orderByDateHired.isPresent()) {
			throw new IllegalStateException("No Records Found!!");
		}
		
		return Utils.getFixList(orderByDateHired.get());
	}
	
	public List<Person> getPersonsOrderByGradeWeightedAverage() {
		List<Person> orderByGradeWeightedAverage = personDao.findAll();
		if(orderByGradeWeightedAverage.isEmpty()) {
			throw new IllegalStateException("No Records Found!!");
		}
		Collections.sort(orderByGradeWeightedAverage, new Comparator<Person>() {
			@Override
			public int compare(Person person1, Person person2) {
				return Double.compare(person1.getGradeWeightedAverage(), person2.getGradeWeightedAverage());
			}
		});
		
		
		return Utils.getFixList(orderByGradeWeightedAverage);
	}
}
