package com.udacity.jdnd.course3.critter.pet;

import com.udacity.jdnd.course3.critter.user.Customer;
import com.udacity.jdnd.course3.critter.user.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class PetService {

    private final PetRepository petRepository;

    private final CustomerRepository customerRepository;

    // Save pet
    public Pet savePet(Pet pet,Long customerId){
        Optional<Customer> customerOptional = customerRepository.findById(customerId);
        if (customerOptional.isEmpty()){
            throw new RuntimeException("Customer not found with id: "+ customerId);
        }

        Customer customer = customerOptional.get();
        pet.setCustomer(customer);
        Pet savedPet = petRepository.save(pet);

        customer.getPets().add(savedPet);
        customerRepository.save(customer);

        return savedPet;
    }

    //Get pet by customer id
    public List<Pet> getPetByCustomer(long customerId){
        return petRepository.findByCustomerId(customerId);
    }

    // Get pet by id
    public Pet getPetById(Long petId){
        return petRepository.findById(petId)
                .orElseThrow(() ->
                        new RuntimeException("Pet not found with id: "+ petId));
    }

    //Get all pets
    public List<Pet> getAllPets(){
        return petRepository.findAll();
    }
}
