package com.walking.inheritance.validator;

import com.walking.inheritance.domain.AdultPerson;
import com.walking.inheritance.domain.GenderType;
import com.walking.inheritance.model.dto.CreatePersonRequest;
import com.walking.inheritance.service.EntityManagerHelper;
import com.walking.inheritance.utils.ValidationUtils;

import java.util.Objects;

// Императивная валидация - достаточно грубое решение в большинстве случаев.
// Но так как основная задача практики заключается не в этом, использован наиболее дешевый подход
public class CreatePersonRequestValidator implements Validator<CreatePersonRequest> {
    private final EntityManagerHelper entityManagerHelper;

    public CreatePersonRequestValidator(EntityManagerHelper entityManagerHelper) {
        this.entityManagerHelper = entityManagerHelper;
    }

    @Override
    public void validate(CreatePersonRequest request) {
        validateByCategory(request);
        validateCommon(request);
    }

    private void validateCommon(CreatePersonRequest request) {
        Objects.requireNonNull(request.getFirstName());
        Objects.requireNonNull(request.getLastName());
        Objects.requireNonNull(request.getBirthDate());
        Objects.requireNonNull(request.getGender());
        Objects.requireNonNull(request.getAddress());
    }

    private void validateByCategory(CreatePersonRequest request) {
        var category = request.getCategory();

        switch (category) {
            case ADULT -> validateAdult(request);
            case CHILD -> validateChild(request);
            default -> throw new IllegalArgumentException("Некорректное значение категории: %s".formatted(category));
        }
    }

    private void validateAdult(CreatePersonRequest request) {
        Objects.requireNonNull(request.getMonthlyIncome());

//        Не самая лучшая практика - jakarta.validation предоставляет более поддерживаемый API для подобных валидаций.
//        Основная проблема данного подхода - при переименовании поля в сообщении об ошибке
//        будет использовано устаревшее имя. Но без углубления в рефлексию или сторонние библиотеки
//        дешевых альтернатив нет
        ValidationUtils.requireNull(request.getMotherId(), "motherId");
        ValidationUtils.requireNull(request.getFatherId(), "fatherId");

    }

    private void validateChild(CreatePersonRequest request) {
        Objects.requireNonNull(request.getMotherId());
        Objects.requireNonNull(request.getFatherId());

        ValidationUtils.requireNull(request.getJob(), "job");
        ValidationUtils.requireNull(request.getMonthlyIncome(), "monthlyIncome");

        entityManagerHelper.runTransactionalNoResult(em -> {
            var mother = em.find(AdultPerson.class, request.getMotherId());

            if (mother == null || mother.getGender() != GenderType.FEMALE) {
                throw new IllegalArgumentException("Идентификатор матери персоны не найден не валиден");
            }

            var father = em.find(AdultPerson.class, request.getFatherId());

            if (father == null || father.getGender() != GenderType.MALE) {
                throw new IllegalArgumentException("Идентификатор матери персоны не найден не валиден");
            }
        });
    }

}
