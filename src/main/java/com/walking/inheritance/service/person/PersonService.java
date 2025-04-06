package com.walking.inheritance.service.person;

import com.walking.inheritance.domain.AdultPerson;
import com.walking.inheritance.domain.Child;
import com.walking.inheritance.domain.Person;
import com.walking.inheritance.domain.PersonCategory;
import com.walking.inheritance.model.UpdatePersonItem;
import com.walking.inheritance.repository.AdultPersonRepository;
import com.walking.inheritance.repository.ChildRepository;
import com.walking.inheritance.repository.PersonRepository;
import com.walking.inheritance.service.EntityManagerHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PersonService {
    private final EntityManagerHelper entityManagerHelper;

    private final PersonRepository personRepository;
    private final AdultPersonRepository adultPersonRepository;
    private final ChildRepository childRepository;

    public PersonService(EntityManagerHelper entityManagerHelper, PersonRepository personRepository,
                         AdultPersonRepository adultPersonRepository, ChildRepository childRepository) {
        this.entityManagerHelper = entityManagerHelper;
        this.personRepository = personRepository;
        this.adultPersonRepository = adultPersonRepository;
        this.childRepository = childRepository;
    }

    public Person getById(UUID id) {
        return entityManagerHelper.runTransactional(em -> em.find(Person.class, id));
    }

    public List<Person> getAll(PersonCategory category) {
        return entityManagerHelper.runTransactional(em -> {
            if (category == null) {
                return personRepository.findAll(em);
            }

            var persons = switch (category) {
                case CHILD -> childRepository.findAll(em);
                case ADULT -> adultPersonRepository.findAll(em);
            };

            return new ArrayList<>(persons);
        });
    }

    public Person create(Person person) {
        return entityManagerHelper.runTransactional(em -> {
            if (person instanceof Child child) {
                var mother = em.find(AdultPerson.class, child.getMotherId());
                child.setMother(mother);

                var father = em.find(AdultPerson.class, child.getFatherId());
                child.setFather(father);
            }

            em.persist(person);

            return person;
        });
    }

    public Person update(UpdatePersonItem personItem) {
        return entityManagerHelper.runTransactional(em -> {
            var person = em.find(Person.class, personItem.getId());

            if (person instanceof AdultPerson adult) {
                adult.setJob(personItem.getJob());
                adult.setMonthlyIncome(personItem.getMonthlyIncome());
            }

            person.setFirstName(personItem.getFirstName());
            person.setLastName(personItem.getLastName());
            person.setAddress(personItem.getAddress());

            return person;
        });
    }
}
