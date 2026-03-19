package com.udacity.jdnd.course3.critter.schedule;

import com.udacity.jdnd.course3.critter.pet.Pet;
import com.udacity.jdnd.course3.critter.pet.PetRepository;
import com.udacity.jdnd.course3.critter.user.Employee;
import com.udacity.jdnd.course3.critter.user.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    private final PetRepository petRepository;

    private final EmployeeRepository employeeRepository;

    // create schedule
    public  Schedule createSchedule(Schedule schedule, List<Long> employeeId,List<Long> petId){
        List<Employee> employees = employeeRepository.findAllById(employeeId);
        List<Pet> pets = petRepository.findAllById(petId);
        schedule.setEmployees(employees);
        schedule.setPets(pets);
        return scheduleRepository.save(schedule);
    }

    // Get all schedule
    public List<Schedule> getAllSchedule(){
        return scheduleRepository.findAll();
    }

    // get schedule for pet
    public List<Schedule> getScheduleForPet(Long petId){
        return scheduleRepository.findByPets_Id(petId);
    }

    // get schedule for employee
    public List<Schedule> getScheduleForEmployee(Long employeeId){
        return scheduleRepository.findByEmployees_Id(employeeId);
    }

    // get schedule for customer
    public List<Schedule> getScheduleForCustomer(Long customerId){
        List<Pet> pet = petRepository.findByCustomerId(customerId);
        return pet.stream()
                        .flatMap(p -> scheduleRepository.findByPets_Id(p.getId()).stream())
                        .distinct()
                        .toList();

    }
}
