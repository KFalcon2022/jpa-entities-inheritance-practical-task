package com.walking.inheritance.repository;

import com.walking.inheritance.domain.Person;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.UUID;

public class PersonRepository {
    public List<Person> findAll(EntityManager em) {
        return em.createQuery("select p from Person p", Person.class)
                .getResultList();
    }

    public List<Person> findFamilyByParentIds(UUID motherId, UUID fatherId, EntityManager em) {
//        TREAT() - Одна из специфичных функций JPQL, позволяющих делать downcast для иерархий JPA Entities
        var jpql = """
                select p from Person p
                where p.id = :fatherId
                    or p.id = :motherId
                    or treat(p as Child).motherId = :motherId
                    or treat(p as Child).fatherId = :fatherId
                """;

        return em.createQuery(jpql, Person.class)
                .setParameter("motherId", motherId)
                .setParameter("fatherId", fatherId)
                .getResultList();
    }
}
