package com.exist.ecc.app;
import java.util.List;


import com.exist.service.PersonService;
import com.exist.model.Person;
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
@ComponentScan(basePackageClasses = {com.exist.service.PersonService.class})
@EntityScan("com.exist.model")
@RequestMapping(path = "api/v1/person")
public class PersonResource {
	
	private  final PersonService personService;
	
	@Autowired
	public PersonResource(PersonService personService) {
		this.personService = personService;
	}
	
	@GetMapping
	public List<Person> getPersons() {
		return personService.getPersons();
	}
	
	@PostMapping
	public void addPerson(@RequestBody Person person){
		personService.addPerson(person);
	}
	
	@DeleteMapping(path = "{personId}")
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
	
}