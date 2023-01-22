package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.entity.Customer;
import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.entity.Schedule;
import com.udacity.jdnd.course3.critter.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ScheduleService {
    @Autowired
    private ScheduleRepository scheduleRepository;
    @Autowired
    private CustomerService customerService;

    public Schedule saveSchedule(Schedule schedule) {
        return scheduleRepository.save(schedule);
    }

    public List<Schedule> getAllSchedules() {
        return scheduleRepository.findAll();
    }

    public List<Schedule> getSchedulesByPetId(Long petId) {
        return scheduleRepository.findAllByPets_Id(petId);
    }

    public List<Schedule> getSchedulesByEmployeeId(Long employeeId) {
        return scheduleRepository.findAllByEmployees_Id(employeeId);
    }

    public List<Schedule> getSchedulesByCustomerId(Long customerId) {
        Customer customer = customerService.getCustomerById(customerId);
        List<Pet> petsOwnedByCustomer = customer.getPetsOwned();
        List<Schedule> schedulesForCustomer = new ArrayList<>();
        petsOwnedByCustomer
                .forEach(pet -> schedulesForCustomer.addAll(getSchedulesByPetId(pet.getId())));
        return schedulesForCustomer;
    }
}
