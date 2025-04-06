package com.walking.inheritance.converter;

import com.walking.inheritance.domain.AdultPerson;
import com.walking.inheritance.domain.Child;
import com.walking.inheritance.model.UpdatePersonItem;
import com.walking.inheritance.model.dto.CreatePersonRequest;
import com.walking.inheritance.model.dto.UpdatePersonRequest;

public class UpdatePersonRequestConverter extends AbstractConverter<UpdatePersonRequest, UpdatePersonItem> {

    @Override
    public UpdatePersonItem convert(UpdatePersonRequest source) {
        var target = new UpdatePersonItem();

        target.setId(source.getId());
        target.setFirstName(source.getFirstName());
        target.setLastName(source.getLastName());
        target.setAddress(source.getAddress());
        target.setJob(source.getJob());
        target.setMonthlyIncome(source.getMonthlyIncome());

        return target;
    }
}
