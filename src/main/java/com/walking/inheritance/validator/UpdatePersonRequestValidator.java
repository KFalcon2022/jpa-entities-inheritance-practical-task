package com.walking.inheritance.validator;

import com.walking.inheritance.domain.AdultPerson;
import com.walking.inheritance.model.dto.UpdatePersonRequest;
import com.walking.inheritance.service.EntityManagerHelper;
import com.walking.inheritance.utils.ValidationUtils;

import java.util.Objects;

public class UpdatePersonRequestValidator implements Validator<UpdatePersonRequest> {
    private final EntityManagerHelper entityManagerHelper;

    public UpdatePersonRequestValidator(EntityManagerHelper entityManagerHelper) {
        this.entityManagerHelper = entityManagerHelper;
    }

    @Override
    public void validate(UpdatePersonRequest request) {
        Objects.requireNonNull(request.getFirstName());
        Objects.requireNonNull(request.getLastName());
        Objects.requireNonNull(request.getAddress());

        var adult = entityManagerHelper.getEntityManager()
                .find(AdultPerson.class, request.getId());

        if (adult != null) {
            return;
        }

        ValidationUtils.requireNull(request.getJob(), "job");
        ValidationUtils.requireNull(request.getMonthlyIncome(), "monthlyIncome");
    }

}
