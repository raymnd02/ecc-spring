package com.exist.model;
import javax.persistence.Table;
import javax.persistence.Entity; 
import javax.persistence.Id;  
import javax.persistence.SequenceGenerator;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import java.io.Serializable;
@Entity
@Table
public class Contact implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int contactId;
	private long landLine;
	private long mobileNumber;
	private String email;
	
	@Transient
	private Person person;
	
	public Contact(){}
		
	public Contact(long landLine,long mobileNumber,String email) {
		// this.contactId = contactId;
		this.landLine = landLine;
		this.mobileNumber = mobileNumber;
		this.email = email;
	}
	public Contact(long landLine,long mobileNumber,String email,Person person) {
		
		this.landLine = landLine;
		this.mobileNumber = mobileNumber;
		this.email = email;
		this.person = person;
	}
	
	public void setContactId(int contactId) {
		this.contactId = contactId;
	}
	
	public int getContactId() {
		return this.contactId;
	}
	
	
	public void setLandLine(long landLine) {
		this.landLine = landLine;
	}
	
	public long getLandLine() {
		return this.landLine;
	}
	
	public void setMobileNumber(long mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	
	public long getMobileNumber() {
		return this.mobileNumber;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getEmail() {
		return this.email;
	}
	
	public void setPerson(Person person) {
		this.person = person;
	}
	
	public Person getPerson() {
		return this.person;
	}
}
