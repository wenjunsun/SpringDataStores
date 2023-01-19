package com.udacity.jdnd.course3.critter.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String phoneNumber;
    private String notes;
    // One customer can own multiple pets. Doesn't make much sense
    // for multiple customers to own one pet.
    @OneToMany
    private List<Pet> petsOwned;

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
