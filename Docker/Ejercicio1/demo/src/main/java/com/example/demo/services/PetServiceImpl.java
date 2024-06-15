package com.example.demo.services;

import org.springframework.stereotype.Service;

import com.example.demo.models.Pet;
import com.example.demo.repositories.PetRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class PetServiceImpl implements PetService {
    
    private final PetRepository repository;

    @Override
    public Pet getPetById(Long id) {
        log.info("Obteneindo la mascota {}", id);
        return repository.findById(id).orElseThrow();
    }

    @Override
    public Iterable<Pet> getAllPets() {
        log.info("Obteniendo todas las mascotas");
        return repository.findAll();
    }

    @Override
    public Pet addPet(Pet pet) {
        log.info("Agregando mascota {}", pet);
        return repository.save(pet);
    }

    @Override
    public Pet updatePet(Pet pet, Long id) {
        log.info("Actualizando mascota {}", pet);
        Pet updated = repository.findById(id).orElseThrow();
        updated.setAge(pet.getAge());
        updated.setName(pet.getName());
        updated.setSpecies(pet.getSpecies());
        updated.setTag(pet.getTag());

        return repository.save(updated);

    }

    @Override
    public Pet deletePet(Long id) {
        log.info("Eliminando mascota {}", id);
        Pet deleted = repository.findById(id).orElseThrow();
        repository.deleteById(id);

        log.info("Mascota eliminada {}", deleted);

        return deleted;
    }
    
}
