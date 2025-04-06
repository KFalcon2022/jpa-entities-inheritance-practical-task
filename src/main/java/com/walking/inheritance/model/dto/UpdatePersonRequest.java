package com.walking.inheritance.model.dto;

import java.util.UUID;

public class UpdatePersonRequest {
    private UUID id;

    private String firstName;

    private String lastName;

    private String address;

    private Double monthlyIncome;

    private String job;

    public UUID getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
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

    public void setJob(String job) {
        this.job = job;
    }
}
