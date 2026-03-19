package com.udacity.jdnd.course3.critter.schedule;

import com.udacity.jdnd.course3.critter.pet.Pet;
import com.udacity.jdnd.course3.critter.user.Employee;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Handles web requests related to Schedules.
 */
@RestController
@RequestMapping("/schedule")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    @PostMapping
    public ScheduleDTO createSchedule(@RequestBody ScheduleDTO scheduleDTO) {
        Schedule schedule = convertToSchedule(scheduleDTO);
        Schedule savedSchedule = scheduleService.createSchedule(
                schedule,scheduleDTO.getEmployeeIds(),scheduleDTO.getPetIds()
        );
        return convertToScheduleDTO(savedSchedule);
    }

    @GetMapping
    public List<ScheduleDTO> getAllSchedules() {

        return scheduleService.getAllSchedule().stream()
                .map(this::convertToScheduleDTO)
                .toList();
    }

    @GetMapping("/pet/{petId}")
    public List<ScheduleDTO> getScheduleForPet(@PathVariable long petId) {

        return scheduleService.getScheduleForPet(petId).stream()
                .map(this::convertToScheduleDTO)
                .toList();
    }

    @GetMapping("/employee/{employeeId}")
    public List<ScheduleDTO> getScheduleForEmployee(@PathVariable long employeeId) {
        return scheduleService.getScheduleForEmployee(employeeId).stream()
                .map(this::convertToScheduleDTO)
                .toList();

    }

    @GetMapping("/customer/{customerId}")
    public List<ScheduleDTO> getScheduleForCustomer(@PathVariable long customerId) {
        return scheduleService.getScheduleForCustomer(customerId).stream()
                .map(this::convertToScheduleDTO)
                .toList();
    }

    // helper methods

    private Schedule convertToSchedule(ScheduleDTO scheduleDTO){
        Schedule schedule = new Schedule();
        BeanUtils.copyProperties(scheduleDTO,schedule);
        return schedule;
    }

    private ScheduleDTO convertToScheduleDTO(Schedule schedule){
        ScheduleDTO dto = new ScheduleDTO();
        BeanUtils.copyProperties(schedule,dto);

        dto.setEmployeeIds(
                schedule.getEmployees().stream()
                        .map(Employee::getId)
                        .toList()
        );

        dto.setPetIds(
                schedule.getPets().stream()
                        .map(Pet::getId)
                        .toList()
        );
        return dto;
    }
}
