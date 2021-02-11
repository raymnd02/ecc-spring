package com.exist.service;
import java.util.Optional;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.*;
import com.exist.model.Role;
import com.exist.model.Person;
import com.exist.dao.RoleDao;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Service
@EnableJpaRepositories("com.exist.dao.RoleDao")
@ComponentScan(basePackages  = {"com.exist.dao"})
public class RoleService {
	
	private final RoleDao roleDao;
	
	@Autowired
	public RoleService(RoleDao roleDao) {
		this.roleDao = roleDao;
	}
	
	public List<Role> getRoles() {
		List<Role> returnRoles = roleDao.findAll();
		returnRoles.stream()
			.forEach(rolePerson -> {
				rolePerson.getPersonRole().stream()
					.forEach(eachPerson -> {
						List<Role> roles = new ArrayList<>();
						eachPerson.setRole(roles);
					});
			});
		return returnRoles;
	}
	
	public void addRole(Role role) {
		Optional<Role> roleOptional = roleDao.findRoleByRole(role.getRole());
		if(roleOptional.isPresent()) {
			throw new IllegalStateException("Role is already exist!!");
		}
		roleDao.save(role);
	}	
	
	public void editRole(Role role,int roleId) {
		boolean exists = roleDao.existsById(roleId);
		if(!exists) {
			throw new IllegalStateException("Role id " + roleId + " does not exists");
		}
		Optional<Role> roleOptional = roleDao.findRoleByRoleEdit(role.getRole(),roleId);
		if(roleOptional.isPresent()) {
			throw new IllegalStateException("Role " + role.getRole() + " is exists");
		}
		role.setRoleId(roleId);
		roleDao.save(role);
	}
	
		
	public void deleteRole(int roleId) {
		boolean exists = roleDao.existsById(roleId);
		if(!exists) {
			throw new IllegalStateException("Role id " + roleId + " does not exists");
		}
		// Optional<Role> roleOptional = roleDao.findById(roleId);
		// List<Person> persons = new ArrayList<>();
		// Role role = roleOptional.get();
		// role.setPersonRole(persons);
		// roleDao.save(role);
		roleDao.deleteById(roleId);
	}
	
	public Role findById(int roleId) {
		Optional<Role> roleOptional = roleDao.findById(roleId);
		if(!roleOptional.isPresent()) {
			throw new IllegalStateException("Role id " + roleId + " does not exists");
		}
		return roleOptional.get();
	}
	public void save(Role role) {
		Role updateRole = new Role();
		updateRole.setRoleId(role.getRoleId());
		updateRole.setRole(role.getRole());
		roleDao.save(updateRole);
	}
	
}
