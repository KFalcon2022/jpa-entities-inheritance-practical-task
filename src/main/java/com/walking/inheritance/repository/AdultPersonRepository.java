package com.walking.inheritance.repository;

import com.walking.inheritance.domain.AdultPerson;
import jakarta.persistence.EntityManager;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

public class AdultPersonRepository {
    public List<AdultPerson> findAll(EntityManager em) {
        return em.createQuery("select a from AdultPerson a", AdultPerson.class)
                .getResultList();
    }

    public List<AdultPerson> findAllByIdIn(Collection<UUID> ids, EntityManager em) {
        return em.createQuery("select a from AdultPerson a where a.id in :ids", AdultPerson.class)
                .setParameter("ids", ids)
                .getResultList();
    }
}
