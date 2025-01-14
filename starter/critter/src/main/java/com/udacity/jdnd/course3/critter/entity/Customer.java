package com.udacity.jdnd.course3.critter.entity;

import org.hibernate.annotations.Nationalized;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Nationalized
    private String name;
    // don't think any phone number in the world can have more than 30 digits.
    @Column(length = 30)
    private String phoneNumber;
    @Nationalized
    @Column(length = 1000) // default length of 255 might be too short for notes.
    private String notes;
    // One customer can own multiple pets. Doesn't make much sense
    // for multiple customers to own one pet.
    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    private List<Pet> petsOwned;

    public void addPet(Pet p) {
        if (petsOwned == null) {
            petsOwned = new ArrayList<>();
        }
        petsOwned.add(p);
    }

    public Customer() {
    }

    public Customer(Long id, String name, String phoneNumber, String notes, List<Pet> petsOwned) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.notes = notes;
        this.petsOwned = petsOwned;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public List<Pet> getPetsOwned() {
        return petsOwned;
    }

    public void setPetsOwned(List<Pet> petsOwned) {
        this.petsOwned = petsOwned;
    }
}
