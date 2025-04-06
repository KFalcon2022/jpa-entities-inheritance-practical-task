package com.walking.inheritance.repository;

import com.walking.inheritance.domain.Child;
import jakarta.persistence.EntityManager;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class ChildRepository {
    public List<Child> findAll(EntityManager em) {
        return em.createQuery("select c from Child c", Child.class)
                .getResultList();
    }

    public List<Child> findAllByBirthDateLessThanEqual(LocalDate birthDate, EntityManager em) {
        return em.createQuery("select c from Child c where c.birthDate <= :birthDate", Child.class)
                .setParameter("birthDate", birthDate)
                .getResultList();
    }

    public List<Child> findAllByMotherId(UUID motherId, EntityManager em) {
        return em.createQuery("select c from Child c where c.motherId = :motherId", Child.class)
                .setParameter("motherId", motherId)
                .getResultList();
    }

    public List<Child> findAllByFatherId(UUID fatherId, EntityManager em) {
        return em.createQuery("select c from Child c where c.fatherId = :fatherId", Child.class)
                .setParameter("fatherId", fatherId)
                .getResultList();
    }
}
