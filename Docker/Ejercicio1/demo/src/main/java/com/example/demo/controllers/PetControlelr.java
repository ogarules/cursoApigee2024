package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.Pet;
import com.example.demo.services.PetService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/pets")
public class PetControlelr {
    
    @Autowired
    private PetService service;

    @GetMapping("/{id}")
    public Pet getPetById(@PathVariable Long id) {
        log.info("Controller => obteniendo mascota por id {}", id);
        return service.getPetById(id);
    }

    @GetMapping
    public Iterable<Pet> getAllpets() {
        log.info("Controller => getting all pets");
        return service.getAllPets();
    }
    
    @PostMapping
    public Pet addPet(@RequestBody Pet entity) {
        log.info("Controller => agregando mascota {}", entity);
        
        return service.addPet(entity);
    }

    @PutMapping("/{id}")
    public Pet updatePet(@PathVariable Long id, @RequestBody Pet entity) {
        log.info("Controller => Actualizando mascota {} con {}", id, entity);
        
        return service.updatePet(entity, id);
    }

    @DeleteMapping("/{id}")
    public void deletePet(@PathVariable Long id) {
        log.info("Controller => eliminando mascota {}", id);
        
        service.deletePet(id);
    }
}
