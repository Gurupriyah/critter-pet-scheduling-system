package com.udacity.jdnd.course3.critter.pet;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Handles web requests related to Pets.
 */
@RestController
@RequestMapping("/pet")
@RequiredArgsConstructor
public class PetController {

    private final PetService petService;

    @PostMapping
    public PetDTO savePet(@RequestBody PetDTO petDTO) {

        Pet petEntity = convertToEntity(petDTO);
        Pet savedPet = petService.savePet(petEntity,petDTO.getOwnerId());
        return convertToDTO(savedPet);

    }

    @GetMapping("/{petId}")
    public PetDTO getPet(@PathVariable long petId) {

        Pet pet = petService.getPetById(petId);
        return convertToDTO(pet);
    }

    @GetMapping
    public List<PetDTO> getPets(){

        return petService.getAllPets().stream()
                .map(this::convertToDTO)
                .toList();
    }

    @GetMapping("/owner/{ownerId}")
    public List<PetDTO> getPetsByOwner(@PathVariable long ownerId) {

        return petService.getPetByCustomer(ownerId).stream()
                .map(this::convertToDTO)
                .toList();

    }

    // helper method
    private Pet convertToEntity(PetDTO petDTO){
        Pet pet = new Pet();
        BeanUtils.copyProperties(petDTO, pet);
        return pet;
    }

    private PetDTO convertToDTO(Pet pet){
        PetDTO petDTO = new PetDTO();
        BeanUtils.copyProperties(pet,petDTO);
        petDTO.setOwnerId(pet.getCustomer().getId());
        return petDTO;
    }
}


