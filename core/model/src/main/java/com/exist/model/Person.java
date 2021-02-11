package com.exist.model;
import java.util.*;
import java.text.*;


import javax.persistence.Table;
import javax.persistence.Entity; 
import javax.persistence.Id;  
import javax.persistence.SequenceGenerator;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;


@Entity
@Table
public class Person {
	@Id
	@SequenceGenerator(
		name = "person_sequence",
		sequenceName = "person_sequence",
		allocationSize = 1
	)
	@GeneratedValue(
		strategy = GenerationType.SEQUENCE,
		generator = "person_sequence"
	)
	private Long personId;

	private String firstName;
	private String middleName;
	private String lastName;
	private String suffixName;
	private String titleName;
	
	
	private int streetNo;
	private String barangay;
	private String municipalityOrCity;
	private int zipcode;
	
	private Date birthday;
	private double gradeWeightedAverage;
	private Date dateHired;
	private boolean currentEmployed;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "person_contact",referencedColumnName = "personId")
	private List<Contact> contact = new ArrayList<>();
	
	
	@ManyToMany(targetEntity = Role.class,cascade = CascadeType.ALL )
    // @JoinTable(
            // name = "person_role",
            // joinColumns = {@JoinColumn(name = "personId")},
            // inverseJoinColumns = {@JoinColumn(name = "roleId")}
    // )
	private List<Role> rolePerson = new ArrayList<>();
	
	public Person(){}
	public Person(String firstName,
					String middleName,
					String lastName,
					String suffixName,
					String titleName,
					int streetNo,
					String barangay,
					String municipalityOrCity,
					int zipcode,
					String birthday,
					double gradeWeightedAverage,
					String dateHired,
					boolean current_employed) {
		// this.personId = personId;
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.suffixName = suffixName;
		this.titleName = titleName;
		this.streetNo = streetNo;
		this.barangay = barangay;
		this.municipalityOrCity = municipalityOrCity;
		this.zipcode = zipcode;
		this.gradeWeightedAverage = gradeWeightedAverage;
		this.currentEmployed = current_employed;
		
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-DD"); 
		try {
			this.birthday = (Date)formatter.parse(birthday);
			this.dateHired = (Date)formatter.parse(dateHired);
		}catch(ParseException e){
		}
		// this.contact = contact;
	}
	
	
	public Person(String firstName,
					String middleName,
					String lastName,
					String suffixName,
					String titleName,
					int streetNo,
					String barangay,
					String municipalityOrCity,
					int zipcode,
					String birthday,
					double gradeWeightedAverage,
					String dateHired,
					boolean current_employed,
					List<Contact> contact) {
		// this.personId = personId;
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.suffixName = suffixName;
		this.titleName = titleName;
		this.streetNo = streetNo;
		this.barangay = barangay;
		this.municipalityOrCity = municipalityOrCity;
		this.zipcode = zipcode;
		this.gradeWeightedAverage = gradeWeightedAverage;
		this.currentEmployed = current_employed;
		this.contact = contact;
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-DD"); 
		try {
			this.birthday = (Date)formatter.parse(birthday);
			this.dateHired = (Date)formatter.parse(dateHired);
		}catch(ParseException e){
		}
		// this.contact = contact;
	}
	
	public void setStreetNo(int streetNo) {
		this.streetNo = streetNo;
	}
	
	public int getStreetNo() {
		return this.streetNo;
	}
	
	public void setBarangay(String barangay) {
		this.barangay = barangay;
	}
	
	public String getBarangay() {
		return this.barangay;
	}
	
	public void setMunicipalityOrCity(String municipalityOrCity) {
		this.municipalityOrCity = municipalityOrCity;
	}
	
	public String getMunicipalityOrCity() {
		return this.municipalityOrCity;
	}
	
	public void setZipcode(int zipcode) {
		this.zipcode = zipcode;
	}
	
	public int getZipcode() {
		return this.zipcode;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getLastName() {
		return this.lastName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getFirstName() {
		return this.firstName;
	}
	
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	
	public String getMiddleName() {
		return this.middleName;
	}
	
	public void setSuffixName(String suffixName) {
		this.suffixName = suffixName;
	}
	
	public String getSuffixName() {
		return this.suffixName;
	}
	
	public void setTitleName(String titleName) {
		this.titleName = titleName;
	}
	
	public String getTitleName() {
		return this.titleName;
	}
	
	public void setRole(List<Role> rolePerson) {
		this.rolePerson = rolePerson;
	}
	
	public List<Role> getRole() {
		return this.rolePerson;
	}
	
	public void setContact(List<Contact> contact) {
		this.contact = contact;
	}
	
	public List<Contact> getContact() {
		return this.contact;
	}
	
	public void setPersonId(Long personId) {
		this.personId = personId;
	}
	public Long getPersonId() {
		return this.personId;
	}
	

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public Date getBirthday() {
		return this.birthday;
	}
	
	
	public void setGradeWeightedAverage(double gradeWeightedAverage) {
		this.gradeWeightedAverage = gradeWeightedAverage;
	}
	public double getGradeWeightedAverage() {
		return this.gradeWeightedAverage;
	}
	
	
	public void setDateHired(Date dateHired) {
		this.dateHired = dateHired;
	}
	public Date getDateHired() {
		return this.dateHired;
	}
	
	
	public void setCurrentEmployed(boolean currentEmployed) {
		this.currentEmployed = currentEmployed;
	}
	public boolean getCurrentEmployed() {
		return this.currentEmployed;
	}
	
}
