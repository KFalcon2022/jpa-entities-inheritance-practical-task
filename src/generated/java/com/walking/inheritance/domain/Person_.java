package com.walking.inheritance.domain;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@StaticMetamodel(Person.class)
@Generated("org.hibernate.processor.HibernateProcessor")
public abstract class Person_ {

	public static final String FIRST_NAME = "firstName";
	public static final String LAST_NAME = "lastName";
	public static final String ADDRESS = "address";
	public static final String GENDER = "gender";
	public static final String CREATED = "created";
	public static final String ID = "id";
	public static final String BIRTH_DATE = "birthDate";
	public static final String UPDATED = "updated";

	
	/**
	 * @see com.walking.inheritance.domain.Person#firstName
	 **/
	public static volatile SingularAttribute<Person, String> firstName;
	
	/**
	 * @see com.walking.inheritance.domain.Person#lastName
	 **/
	public static volatile SingularAttribute<Person, String> lastName;
	
	/**
	 * @see com.walking.inheritance.domain.Person#address
	 **/
	public static volatile SingularAttribute<Person, String> address;
	
	/**
	 * @see com.walking.inheritance.domain.Person#gender
	 **/
	public static volatile SingularAttribute<Person, GenderType> gender;
	
	/**
	 * @see com.walking.inheritance.domain.Person#created
	 **/
	public static volatile SingularAttribute<Person, LocalDateTime> created;
	
	/**
	 * @see com.walking.inheritance.domain.Person#id
	 **/
	public static volatile SingularAttribute<Person, UUID> id;
	
	/**
	 * @see com.walking.inheritance.domain.Person
	 **/
	public static volatile EntityType<Person> class_;
	
	/**
	 * @see com.walking.inheritance.domain.Person#birthDate
	 **/
	public static volatile SingularAttribute<Person, LocalDate> birthDate;
	
	/**
	 * @see com.walking.inheritance.domain.Person#updated
	 **/
	public static volatile SingularAttribute<Person, LocalDateTime> updated;

}

