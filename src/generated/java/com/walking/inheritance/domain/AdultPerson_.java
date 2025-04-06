package com.walking.inheritance.domain;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(AdultPerson.class)
@Generated("org.hibernate.processor.HibernateProcessor")
public abstract class AdultPerson_ extends com.walking.inheritance.domain.Person_ {

	public static final String JOB = "job";
	public static final String MONTHLY_INCOME = "monthlyIncome";

	
	/**
	 * @see com.walking.inheritance.domain.AdultPerson#job
	 **/
	public static volatile SingularAttribute<AdultPerson, String> job;
	
	/**
	 * @see com.walking.inheritance.domain.AdultPerson
	 **/
	public static volatile EntityType<AdultPerson> class_;
	
	/**
	 * @see com.walking.inheritance.domain.AdultPerson#monthlyIncome
	 **/
	public static volatile SingularAttribute<AdultPerson, Double> monthlyIncome;

}

