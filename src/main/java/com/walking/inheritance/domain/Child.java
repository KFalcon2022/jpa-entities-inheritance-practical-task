package com.walking.inheritance.domain;


import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.util.UUID;

@Entity
@DiscriminatorValue("CHILD")
public class Child extends Person {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_mother")
    private AdultPerson mother;

    @Column(name = "fk_mother", nullable = false, updatable = false, insertable = false)
    private UUID motherId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_father")
    private AdultPerson father;

    @Column(name = "fk_father", nullable = false, updatable = false, insertable = false)
    private UUID fatherId;

    public AdultPerson getMother() {
        return mother;
    }

    public void setMother(AdultPerson mother) {
        this.mother = mother;
        this.motherId = mother != null ? mother.getId() : null;
    }

    public UUID getMotherId() {
        return motherId;
    }

    public AdultPerson getFather() {
        return father;
    }

    public void setFather(AdultPerson father) {
        this.father = father;
        this.fatherId = father != null ? father.getId() : null;
    }

    public UUID getFatherId() {
        return fatherId;
    }

    //    Поскольку поле помечено как не вставляемое и не обновляемое, мы можем выполнять установку значения,
//    но она не влияет на формируемые JPA INSERT- и UPDATE-запросы
    public void setMotherId(UUID motherId) {
        this.motherId = motherId;
    }

    public void setFatherId(UUID fatherId) {
        this.fatherId = fatherId;
    }
}
