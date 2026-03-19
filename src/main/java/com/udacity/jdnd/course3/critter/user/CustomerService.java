package com.udacity.jdnd.course3.critter.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    // Save customer
    public Customer saveCustomer(Customer customer){
        return customerRepository.save(customer);
    }

    // Get all customers
    public List<Customer> getAllCustomers(){
        return customerRepository.findAll();
    }

    // Get customer by pet
    public Customer getCustomerByPetId(Long petId){
        return customerRepository.findByPets_Id(petId)
                .orElseThrow(() ->
                        new RuntimeException("Customer not found for pet id: "+petId));
    }
}
