package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.entity.Employee;
import com.udacity.jdnd.course3.critter.enums.EmployeeSkill;
import com.udacity.jdnd.course3.critter.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Employee getEmployeeById(Long employeeId) {
        Optional<Employee> employeeOptional = employeeRepository.findById(employeeId);
        if (! employeeOptional.isPresent()) {
            throw new NoSuchElementException("Employee with id " + employeeId + " is not found");
        }
        return employeeOptional.get();
    }

    public void setAvailabilityForEmployee(Set<DayOfWeek> daysAvailable, Long employeeId) {
        Employee employee = getEmployeeById(employeeId);
        employee.setDaysAvailable(daysAvailable);
        // Don't need to call save on employee object here because it is found using
        // repo.findById() method, and that means this employee object is automatically
        // managed by JPA and any change to it is automatically persisted to database.
    }

    // Find employees that are available on a certain day and with a certain set of skills.
    public List<Employee> getEmployeesForService(LocalDate date, Set<EmployeeSkill> skills) {
        // filter on date first
        List<Employee> employeesAvailableOnDate = employeeRepository.findAllByDaysAvailableContaining(date.getDayOfWeek());
        // then filter on skills
        return employeesAvailableOnDate
                .stream()
                .filter(employee -> employee.getSkills().containsAll(skills))
                .collect(Collectors.toList());
    }
}
