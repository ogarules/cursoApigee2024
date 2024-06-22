package com.example.demo.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.Department;
import com.example.demo.repositories.DepartmentRepository;
import com.example.demo.services.EmployeeService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@Slf4j
@RequiredArgsConstructor
public class DepartmentController {
    
    private final DepartmentRepository repository;

    private final EmployeeService service;

    @GetMapping
    public Iterable<Department> getAllDepartments() {
        log.info("K8s obteniendo todos los departamentos");
        return repository.findAll();
    }
    
    @GetMapping("/{id}")
    public Department getDepartmentById(@PathVariable String id) {
        log.info("K8s obteniendo departamento {}", id);
        return repository.findById(id).orElseThrow();
    }
    
    @GetMapping("/organization/{id}")
    public Iterable<Department> getAllDepartmentsByOrganization(@PathVariable String id) {
        log.info("K8s obteniendo departamentos de la organizacion {}", id);
        List<Department> departments = repository.findByOrganizationId(id);

        departments.forEach(d -> d.setEmployees(service.findByDepartmentId(id)));

        return departments;
    }
    
    @PostMapping
    public Department addDepartment(@RequestBody Department entity) {
        log.info("k8s agregando departamentpo {}", entity);
        
        return repository.save(entity);
    }
    

}
