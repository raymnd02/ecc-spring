package com.exist.ecc.app;
import java.util.List;
import java.util.ArrayList;


import com.exist.service.RoleService;
import com.exist.service.PersonService;
import com.exist.model.Role;
import com.exist.model.Person;
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
@ComponentScan(basePackageClasses = {com.exist.service.RoleService.class,com.exist.service.PersonService.class})
@EntityScan("com.exist.model")
@RequestMapping(path = "api/v1/role")
public class RoleResource {

		
	private  final RoleService roleService;
	private  final PersonService personService;
	
	@Autowired
	public RoleResource(RoleService roleService,PersonService personService) {
		this.roleService = roleService;
		this.personService = personService;
	}
	
	@GetMapping
	public List<Role> getRoles() {
		return roleService.getRoles();
	}
	
	@PostMapping
	public void addRole(@RequestBody Role role){
		roleService.addRole(role);
	}
	
	@PostMapping(path = "editRole/{roleId}")
	public void editRole(@RequestBody Role role,@PathVariable("roleId") int roleId){
		roleService.editRole(role,roleId);
	}
	
	@DeleteMapping(path = "deleteRole/{roleId}")
	public void deleteRole(@PathVariable("roleId") int roleId) {
		// List<Person> persons = new ArrayList<>();
		Role role = roleService.findById(roleId);
		personService.deleteRoleConstraints(role);
		// role.setPersonRole(persons);
		// roleService.save(role);
		roleService.deleteRole(roleId);
	}
}