package com.udacity.jdnd.course3.critter.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Set;

@Service
@Transactional
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    // Save Employee
    public Employee saveEmployee(Employee employee){
        return employeeRepository.save(employee);
    }

    // Get employee
    public Employee getEmployee(long id){
        return employeeRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Employee not found with id: "+ id));
    }

    // Set availability
    public void setAvailability(long employeeId, Set<DayOfWeek> daysAvailable){
        Employee employee = getEmployee(employeeId);
        employee.setDaysAvailable(daysAvailable);
        employeeRepository.save(employee);
    }

    // Find employee for service
    public List<Employee> findEmployeeForService(Set<EmployeeSkill> skills, DayOfWeek day){
        return employeeRepository.findAll().stream()
                .filter(e -> e.getDaysAvailable().contains(day) && e.getSkills().containsAll(skills))
                .toList();
    }
}
