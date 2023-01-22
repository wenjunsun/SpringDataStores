package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.entity.Customer;
import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.repository.CustomerRepository;
import com.udacity.jdnd.course3.critter.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Transactional
public class PetService {
    @Autowired
    private PetRepository petRepository;
    @Autowired
    private CustomerRepository customerRepository;

    // This method needs to keep the bidirectional relationship between
    // pet and customer in sync.
    public Pet savePet(Pet pet) {
        Pet savedPet = petRepository.save(pet);
        Customer owner = savedPet.getOwner();
        owner.addPet(savedPet);
        customerRepository.save(owner);
        return savedPet;
    }

    public Pet getPetById(Long id) {
        Optional<Pet> petOptional = petRepository.findById(id);
        if (! petOptional.isPresent()) {
            throw new NoSuchElementException("Pet with id " + id + " is not found.");
        }
        return petOptional.get();
    }

    public List<Pet> getAllPets() {
        return petRepository.findAll();
    }

    public List<Pet> getPetsByOwnerId(Long ownerId) {
        return petRepository.findAllByOwnerId(ownerId);
    }
}
