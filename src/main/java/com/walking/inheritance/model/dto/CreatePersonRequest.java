package com.walking.inheritance.model.dto;

import com.walking.inheritance.domain.GenderType;
import com.walking.inheritance.domain.PersonCategory;

import java.time.LocalDate;
import java.util.UUID;

public class CreatePersonRequest {
    private PersonCategory category;

    private String firstName;

    private String lastName;

    private LocalDate birthDate;

    private GenderType gender;

    private String address;

    private Double monthlyIncome;

    private String job;

    private UUID motherId;

    private UUID fatherId;

    public PersonCategory getCategory() {
        return category;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public GenderType getGender() {
        return gender;
    }

    public String getAddress() {
        return address;
    }

    public Double getMonthlyIncome() {
        return monthlyIncome;
    }

    public String getJob() {
        return job;
    }

    public UUID getMotherId() {
        return motherId;
    }

    public UUID getFatherId() {
        return fatherId;
    }
}
