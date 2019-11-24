package com.karucell.timetrack.service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.stereotype.Service;

import com.karucell.timetrack.controller.OrganisationDTO;
import com.karucell.timetrack.repository.OrganisationRepository;
import com.karucell.timetrack.repository.entity.OrganisationEntity;
import com.karucell.timetrack.support.ValidationSupport;

/**
 * Service to handle organisations.
 * Implements fetch, create and update methods.
 */

@Service
public class OrganisationService {

    private OrganisationRepository organisationRepository;
    private ValidationSupport validationSupport;
    private OrganisationMapper organisationMapper;

    public OrganisationService(
            OrganisationRepository organisationRepository,
            ValidationSupport validationSupport,
            OrganisationMapper organisationMapper
    ) {
        this.organisationRepository = organisationRepository;
        this.validationSupport = validationSupport;
        this.organisationMapper = organisationMapper;
    }

    public List<OrganisationDTO> fetchOrganisations() {
        return organisationMapper.toOrganisationDTOs(organisationRepository.findAll());
    }

    public Optional<OrganisationDTO> fetchOrganisation(Long orgId) {
        if (orgId != null) {
            Optional<OrganisationEntity> entity = organisationRepository.findById(orgId);
            if (entity.isPresent()) {
                OrganisationEntity organisationEntity = entity.get();
                OrganisationDTO dto = organisationMapper.toOrganisationDTO(organisationEntity);
                return Optional.of(dto);
            }
        }
        return Optional.empty();
    }

    // TODO: refactor to take OrganisationDTO as parameter
    // TODO: this prolly should be moved to mapper when introduced
    // TODO: add tests, after refactoring interface to use DTO
    // Is this method needed at all?
    // Marko: this service is so simple that details of creating new entity can be capsulated directly into createNewOrganisation.
    //        this issue might come up with other entities, particularly when dealing with parent-child relationships,
    //        lets remove this method and create these if needed.
    private OrganisationEntity initNewEntity(OrganisationEntity entity) {
        OrganisationEntity newEntity = new OrganisationEntity();
        newEntity.setId(null);
        newEntity.setName(entity.getName());
        newEntity.setYtjIdentifier(entity.getYtjIdentifier());
        newEntity.setActive(true);
        // newEntity.setProjects(null);
        return newEntity;
    }

    public Optional<OrganisationDTO> createNewOrganisation(OrganisationDTO newOrg) {
        OrganisationEntity newOrgEntity = new OrganisationEntity();
        organisationMapper.valuesFromDTO(newOrg, newOrgEntity);
        validationSupport.validOrThrow(newOrgEntity);
        newOrgEntity.setId(null);
        newOrgEntity.setActive(true);
        newOrgEntity = organisationRepository.save(newOrgEntity);
        return Optional.of(organisationMapper.toOrganisationDTO(newOrgEntity));
    }

    public Optional<OrganisationDTO> updateOrganisation(OrganisationDTO orgWithNewValues) {
        Optional<OrganisationEntity> organisation = organisationRepository.findById(orgWithNewValues.getId());
        if (organisation.isPresent()) {
            OrganisationEntity organisationEntity = organisation.get();
            organisationMapper.valuesFromDTO(orgWithNewValues, organisationEntity);
            validationSupport.validOrThrow(organisationEntity);
            organisationEntity = organisationRepository.save(organisationEntity);
            OrganisationDTO dto = organisationMapper.toOrganisationDTO(organisationEntity);
            return Optional.of(dto);
        } else {
            return Optional.empty();
        }
    }

    // TODO: refactor this to return DTO instead of entity
    Optional<OrganisationEntity> disableOrganisation(Long id) {
        Function<OrganisationEntity, OrganisationEntity> disableOrganisationEntity = organisationEntity -> {
            organisationEntity.setActive(false);
            return organisationEntity;
        };
        return Optional.of(id)
                       .flatMap(organisationRepository::findById)
                       .map(disableOrganisationEntity)
                       .map(organisationRepository::save);
    }
}
