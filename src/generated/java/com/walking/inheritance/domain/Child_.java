package com.walking.inheritance.domain;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import java.util.UUID;

@StaticMetamodel(Child.class)
@Generated("org.hibernate.processor.HibernateProcessor")
public abstract class Child_ extends com.walking.inheritance.domain.Person_ {

	public static final String MOTHER = "mother";
	public static final String MOTHER_ID = "motherId";
	public static final String FATHER = "father";
	public static final String FATHER_ID = "fatherId";

	
	/**
	 * @see com.walking.inheritance.domain.Child#mother
	 **/
	public static volatile SingularAttribute<Child, AdultPerson> mother;
	
	/**
	 * @see com.walking.inheritance.domain.Child#motherId
	 **/
	public static volatile SingularAttribute<Child, UUID> motherId;
	
	/**
	 * @see com.walking.inheritance.domain.Child#father
	 **/
	public static volatile SingularAttribute<Child, AdultPerson> father;
	
	/**
	 * @see com.walking.inheritance.domain.Child#fatherId
	 **/
	public static volatile SingularAttribute<Child, UUID> fatherId;
	
	/**
	 * @see com.walking.inheritance.domain.Child
	 **/
	public static volatile EntityType<Child> class_;

}

