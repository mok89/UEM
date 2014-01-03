package it.unical.uniexam.hibernate.domain;


import java.net.URL;

import it.unical.uniexam.hibernate.domain.utility.Address;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * 
 * @author luigi
 *
 */
@Entity
@Table(name="USER")
@Inheritance(strategy = InheritanceType.JOINED)
public class User {

	public enum TYPE{
		PROFESSOR,STUDENT;
	}
	
	
public User() {
	}

	public User(TYPE type, String name, String surname, URL webSite,
		String password, Address address) {
	super();
	this.type = type;
	this.name = name;
	this.surname = surname;
	this.webSite = webSite;
	this.password = password;
	this.address = address;
}

	@Id
	@Column(name="ID", nullable=false)
	@GeneratedValue
	Long id;
	
	@OneToOne(cascade=CascadeType.ALL)
	Session session;
	
	@Column(name="TYPE")
	TYPE type;
	
	@Column(name="NAME", nullable=false)
	String name;
	
	@Column(name="SURNAME", nullable=false)
	String surname;
	
	@Column(name="WEB_SITE",nullable=true)
	URL webSite;
	
	@Column(name="PASSWORD", nullable=false)
	String password;
	
	@Embedded
	Address address;
	
//	@OneToMany(cascade=CascadeType.ALL)
//	@JoinTable(name="USER_PHONE",
//	joinColumns={
//			@JoinColumn(name="USER_ID")
//			}, 
//	inverseJoinColumns={
//			@JoinColumn(name="PHONE_ID")
//			})
//	Set<PhoneNumber>phoneNumbers=new HashSet<PhoneNumber>();
	
	
	/**
	 * Second part of function
	 */



	public String getPassword() {
		return password;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public URL getWebSite() {
		return webSite;
	}

	public void setWebSite(URL webSite) {
		this.webSite = webSite;
	}

	public TYPE getType() {
		return type;
	}

	public void setType(TYPE type) {
		this.type = type;
	}

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}
	
}
