package com.exist.ecc.app;
import java.util.List;
import java.util.ArrayList;


import com.exist.service.PersonService;
import com.exist.service.RoleService;
import com.exist.model.Person;
import com.exist.model.Role;
import com.exist.model.Contact;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.boot.autoconfigure.domain.EntityScan;


@RestController
@ComponentScan(basePackageClasses = {com.exist.service.PersonService.class,com.exist.service.RoleService.class})
@EntityScan("com.exist.model")
@RequestMapping(path = "api/v1/person")
public class PersonResource {
	
	private  final PersonService personService;
	private  final RoleService roleService;
	
	@Autowired
	public PersonResource(PersonService personService,RoleService roleService) {
		this.personService = personService;
		this.roleService = roleService;
	}
	
	@GetMapping
	public List<Person> getPersons() {
		return personService.getPersons();
	}
	
	@GetMapping(path = "orderBy/{orderBy}")
	public List<Person> getPersonsOrderBy(@PathVariable("orderBy") String orderBy) {
		List<Person> person = new ArrayList<>();
		if(orderBy.equalsIgnoreCase("last name")) {
			person = personService.getPersonsOrderByLastName();
		} else if(orderBy.equalsIgnoreCase("gwa")) {
			person = personService.getPersonsOrderByDateHired();
		} else if(orderBy.equalsIgnoreCase("date hired")) {
			person = personService.getPersonsOrderByGradeWeightedAverage();
		} else {
			throw new IllegalStateException("cant do that sorting method");
		}
		
		return person;
	}
	
	@PostMapping
	public void addPerson(@RequestBody Person person){
		personService.addPerson(person);
	}
	
	@DeleteMapping(path = "delete/{personId}")
	public void deletePerson(@PathVariable("personId") Long personId) {
		personService.deletePerson(personId);
	}
	
	@PostMapping(path = "edit/{personId}")
	public void editPerson(@RequestBody Person person,@PathVariable("personId") Long personId){
		personService.editPerson(person,personId);
	}
	
	@PostMapping(path = "addContact/{personId}")
	public void addContactPerson(@RequestBody Contact contact,@PathVariable("personId") Long personId){
		personService.addContactPerson(contact,personId);
	}
	
	@PutMapping(path = "addPersonRole/{personId}/{roleId}")
	public void addPersonRole(@PathVariable("personId") Long personId,@PathVariable("roleId") int roleId){
		Role role = roleService.findById(roleId);
		personService.addPersonRole(personId,role);
	}
	
	@PutMapping(path = "deletePersonRole/{personId}/{roleId}")
	public void deletePersonRole(@PathVariable("personId") Long personId,@PathVariable("roleId") int roleId){
		personService.deletePersonRole(personId,roleId);
	}
	
}