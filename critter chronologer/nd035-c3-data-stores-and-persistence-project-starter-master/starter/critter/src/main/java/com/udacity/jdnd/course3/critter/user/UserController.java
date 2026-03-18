package com.udacity.jdnd.course3.critter.user;

import com.udacity.jdnd.course3.critter.pet.Pet;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Set;

/**
 * Handles web requests related to Users.
 *
 * Includes requests for both customers and employees. Splitting this into separate user and customer controllers
 * would be fine too, though that is not part of the required scope for this class.
 */
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final CustomerService customerService;

    private final EmployeeService employeeService;

    @PostMapping("/customer")
    public CustomerDTO saveCustomer(@RequestBody CustomerDTO customerDTO){
        Customer customer = convertToCustomer(customerDTO);

        Customer savedCustomer = customerService.saveCustomer(customer);
        return convertToCustomerDTO(savedCustomer);

    }

    @GetMapping("/customer")
    public List<CustomerDTO> getAllCustomers(){

        return customerService.getAllCustomers().stream()
                .map(this::convertToCustomerDTO)
                .toList();
    }

    @GetMapping("/customer/pet/{petId}")
    public CustomerDTO getOwnerByPet(@PathVariable long petId){
        Customer customer= customerService.getCustomerByPetId(petId);
        return convertToCustomerDTO(customer);
    }

    @PostMapping("/employee")
    public EmployeeDTO saveEmployee(@RequestBody EmployeeDTO employeeDTO) {

        Employee employee =convertToEmployee(employeeDTO);
        Employee savedEmployee = employeeService.saveEmployee(employee);
        return  convertToEmployeeDTO(savedEmployee);
    }

    @PostMapping("/employee/{employeeId}")
    public EmployeeDTO getEmployee(@PathVariable long employeeId) {

        Employee employee = employeeService.getEmployee(employeeId);
        return convertToEmployeeDTO(employee);
    }

    @PutMapping("/employee/{employeeId}")
    public void setAvailability(@RequestBody Set<DayOfWeek> daysAvailable, @PathVariable long employeeId) {
        employeeService.setAvailability(employeeId,daysAvailable);
    }

    @GetMapping("/employee/availability")
    public List<EmployeeDTO> findEmployeesForService(@RequestBody EmployeeRequestDTO employeeDTO) {
        Set<EmployeeSkill> skills = employeeDTO.getSkills();
        DayOfWeek day = employeeDTO.getDate().getDayOfWeek();
        return employeeService.findEmployeeForService(skills,day).stream()
                .map(this::convertToEmployeeDTO)
                .toList();
    }

    // helper methods

    private Customer convertToCustomer(CustomerDTO dto){
        Customer customer = new Customer();
        BeanUtils.copyProperties(dto, customer);
        return customer;
    }

    private CustomerDTO convertToCustomerDTO(Customer customer){
        CustomerDTO dto = new CustomerDTO();
        BeanUtils.copyProperties(customer, dto);
        dto.setPetIds(customer.getPets().stream()
                .map(Pet::getId)
                .toList());
        return dto;
    }

    private Employee convertToEmployee(EmployeeDTO dto){
        Employee employee = new Employee();
        BeanUtils.copyProperties(dto,employee);
        return employee;
    }

    private EmployeeDTO convertToEmployeeDTO(Employee employee){
        EmployeeDTO dto = new EmployeeDTO();
        BeanUtils.copyProperties(employee,dto);
        return dto;
    }

}
