package com.walking.inheritance.converter;

import com.walking.inheritance.domain.AdultPerson;
import com.walking.inheritance.domain.Child;
import com.walking.inheritance.domain.Person;
import com.walking.inheritance.model.dto.CreatePersonRequest;

public class CreatePersonRequestConverter extends AbstractConverter<CreatePersonRequest, Person> {
    @Override
    public Person convert(CreatePersonRequest source) {
        var target = switch (source.getCategory()) {
            case CHILD -> buildChild(source);
            case ADULT -> buildAdult(source);
        };

        target.setFirstName(source.getFirstName());
        target.setLastName(source.getLastName());
        target.setBirthDate(source.getBirthDate());
        target.setGender(source.getGender());
        target.setAddress(source.getAddress());

        return target;
    }

    private AdultPerson buildAdult(CreatePersonRequest source) {
        var person = new AdultPerson();

        person.setJob(source.getJob());
        person.setMonthlyIncome(source.getMonthlyIncome());

        return person;
    }

    private Child buildChild(CreatePersonRequest source) {
        var person = new Child();

        person.setMotherId(source.getMotherId());
        person.setFatherId(source.getFatherId());

        return person;
    }
}
