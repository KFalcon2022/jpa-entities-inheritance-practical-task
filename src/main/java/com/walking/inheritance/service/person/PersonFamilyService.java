package com.walking.inheritance.service.person;

import com.walking.inheritance.domain.AdultPerson;
import com.walking.inheritance.domain.Child;
import com.walking.inheritance.domain.GenderType;
import com.walking.inheritance.domain.Person;
import com.walking.inheritance.repository.AdultPersonRepository;
import com.walking.inheritance.repository.ChildRepository;
import com.walking.inheritance.repository.PersonRepository;
import com.walking.inheritance.service.EntityManagerHelper;
import jakarta.persistence.EntityManager;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

public class PersonFamilyService {
    private final EntityManagerHelper entityManagerHelper;

    private final PersonRepository personRepository;
    private final ChildRepository childRepository;
    private final AdultPersonRepository adultPersonRepository;

    public PersonFamilyService(EntityManagerHelper entityManagerHelper, PersonRepository personRepository,
                               ChildRepository childRepository, AdultPersonRepository adultPersonRepository) {
        this.entityManagerHelper = entityManagerHelper;
        this.personRepository = personRepository;
        this.childRepository = childRepository;
        this.adultPersonRepository = adultPersonRepository;
    }

    public List<Person> getByMemberId(UUID id) {
        return entityManagerHelper.runTransactional(em -> {
            var person = em.find(Person.class, id);

            return switch (person) {
                case AdultPerson parent -> getFamilyByAdult(parent, em);
                case Child child ->
                        personRepository.findFamilyByParentIds(child.getMotherId(), child.getFatherId(), em);
                default -> throw new RuntimeException("Неизвестный тип Person: '%s'".formatted(person));
            };
        });
    }

    private List<Person> getFamilyByAdult(AdultPerson parent, EntityManager em) {
        var children = getChildrenByParentId(parent, em);
        var parents = getParentsByChildren(parent, em, children);

        var family = new ArrayList<Person>(children);
        family.addAll(parents);
        family.add(parent);

        return family;
    }

    private List<AdultPerson> getParentsByChildren(AdultPerson adult, EntityManager em, List<Child> children) {
        Function<Child, UUID> parentsSearchingFunction = adult.getGender() == GenderType.MALE
                ? Child::getMotherId
                : Child::getFatherId;

        var parentIds = children.stream()
                .map(parentsSearchingFunction)
                .collect(Collectors.toSet());

        return adultPersonRepository.findAllByIdIn(parentIds, em);
    }

    private List<Child> getChildrenByParentId(AdultPerson adult, EntityManager em) {
        return adult.getGender() == GenderType.MALE
                ? childRepository.findAllByFatherId(adult.getId(), em)
                : childRepository.findAllByMotherId(adult.getId(), em);
    }
}
