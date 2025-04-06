package com.walking.inheritance.domain;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name = "adult_person")
@DiscriminatorValue("ADULT")
@PrimaryKeyJoinColumn(name = "fk_person")
public class AdultPerson extends Person {

    @Column(name = "monthly_income", nullable = false)
    private Double monthlyIncome;

    private String job;

    public Double getMonthlyIncome() {
        return monthlyIncome;
    }

    public void setMonthlyIncome(Double monthlyIncome) {
        this.monthlyIncome = monthlyIncome;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }
}
