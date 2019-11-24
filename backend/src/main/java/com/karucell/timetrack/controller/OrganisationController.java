package com.karucell.timetrack.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.karucell.timetrack.service.OrganisationService;

@RestController
public class OrganisationController {

    private OrganisationService service;

    public OrganisationController(OrganisationService service) {
        this.service = service;
    }

    @GetMapping("/organisations")
    public List<OrganisationDTO> all() {
        return service.fetchOrganisations();
    }

    @GetMapping("/organisations/{id}")
    public Optional<OrganisationDTO> one(@PathVariable Long id) {
        return service.fetchOrganisation(id);
    }

    @PostMapping("/organisations")
    public Optional<OrganisationDTO> newOrganisation(@RequestBody OrganisationDTO organisation) {
        return service.createNewOrganisation((organisation));
    }

    @PutMapping("/organisations")
    public Optional<OrganisationDTO> modifyOrganisation(@RequestBody OrganisationDTO orgWithNewValues) {
        return service.updateOrganisation(orgWithNewValues);
    }
}
