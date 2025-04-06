package com.walking.inheritance.converter;

import com.walking.inheritance.domain.AdultPerson;
import com.walking.inheritance.domain.Child;
import com.walking.inheritance.domain.Person;
import com.walking.inheritance.model.dto.PersonDto;

import java.time.ZoneOffset;

public class PersonConverter extends AbstractConverter<Person, PersonDto> {
    @Override
    public PersonDto convert(Person source) {
        PersonDto target = new PersonDto();

        target.setId(source.getId());
        target.setFirstName(source.getFirstName());
        target.setLastName(source.getLastName());
        target.setBirthDate(source.getBirthDate());
        target.setGender(source.getGender());
        target.setAddress(source.getAddress());
//        Часовой пояс клиента может быть любым, поэтому LocalDateTime не будет показательным
        target.setCreated(source.getCreated().atZone(ZoneOffset.UTC));
        target.setUpdated(source.getUpdated().atZone(ZoneOffset.UTC));

        switch (source) {
            case AdultPerson person -> buildAdult(person, target);
            case Child person -> buildChild(person, target);
            default -> throw new RuntimeException("Неизвестный тип Person");
        }

        return target;
    }

    private void buildChild(Child person, PersonDto target) {
        target.setMotherId(person.getMotherId());
        target.setFatherId(person.getFatherId());
    }

    private void buildAdult(AdultPerson person, PersonDto target) {
        target.setMonthlyIncome(person.getMonthlyIncome());
        target.setJob(person.getJob());
    }
}
