package com.udacity.jdnd.course3.critter.entity;

import com.udacity.jdnd.course3.critter.enums.PetType;
import org.hibernate.annotations.Nationalized;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    // Saving enum as string here so that we can add additional
    // enum values in the middle and not breaking the enum values
    // in database. Saving enum as ordinal means we have to add
    // additional enum values in the end.
    @Enumerated(EnumType.STRING)
    private PetType type;
    @Nationalized
    private String name;
    @ManyToOne
    private Customer owner;
    // According to https://thorben-janssen.com/hibernate-jpa-date-and-time/,
    // java.time.LocalDate can be directly mapped to DATE type in JDBC.
    private LocalDate birthDate;
    @Nationalized
    private String notes;

    // no-arg constructor needed for Spring JPA
    public Pet() {
    }

    public Pet(Long id, PetType type, String name, Customer owner, LocalDate birthDate, String notes) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.owner = owner;
        this.birthDate = birthDate;
        this.notes = notes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PetType getType() {
        return type;
    }

    public void setType(PetType type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Customer getOwner() {
        return owner;
    }

    public void setOwner(Customer owner) {
        this.owner = owner;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
