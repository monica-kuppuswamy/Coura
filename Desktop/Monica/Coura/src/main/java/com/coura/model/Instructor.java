package com.coura.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="INSTRUCTORS")
public class Instructor {
	
	public Instructor() {
		super();
	}
	
	@Id
	@Column(name = "id")
	private Integer id;
	
	public Instructor(Integer id, String firstName, String lastName, String emailId, String researchInterest) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailId = emailId;
		this.researchInterest = researchInterest;
	}

	@Column(name = "firstname")
	private String firstName;
	
	@Column(name = "lastname")
	private String lastName;
	
	@Column(name = "emailId")
	private String emailId;
	
	@Column(name = "research_interest")
	private String researchInterest;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getResearchInterest() {
		return researchInterest;
	}

	public void setResearchInterest(String researchInterest) {
		this.researchInterest = researchInterest;
	}

}
