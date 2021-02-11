package com.exist.model;
import java.util.List;
import javax.persistence.Table;
import javax.persistence.Entity; 
import javax.persistence.Id;  
import javax.persistence.SequenceGenerator;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.ManyToMany;
import javax.persistence.CascadeType;
import javax.persistence.Transient;
import javax.persistence.FetchType;
import java.io.Serializable;
@Entity
@Table
public class Role implements java.io.Serializable{
	@Id
	@SequenceGenerator(
		name = "role_sequence",
		sequenceName = "role_sequence",
		allocationSize = 1
	)
	@GeneratedValue(
		strategy = GenerationType.SEQUENCE,
		generator = "role_sequence"
	)
	private int roleId;
	private String role;
	
	@ManyToMany(targetEntity = Person.class, mappedBy = "rolePerson", cascade = CascadeType.ALL)
	private List<Person> personRole;
	
	public Role(){}
	public Role(String role) {
		this.role = role;
	}
	
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	
	public int getRoleId() {
		return this.roleId;
	}
	
	public void setRole(String role) {
		this.role = role;
	}
	
	public String getRole() {
		return this.role;
	}
	
	public void setPersonRole(List<Person> personRole) {
		this.personRole = personRole;
	}
	
	public List<Person> getPersonRole() {
		return personRole;
	}
	
		
}
