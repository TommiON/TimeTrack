package com.karucell.timetrack.service;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import com.karucell.timetrack.controller.OrganisationDTO;
import com.karucell.timetrack.repository.entity.OrganisationEntity;

/**
 * TODO: Refactor string into constant class and wire here, like:
 *
 * public class MapperConfig {
 *     public static final String COMPONENT_MODEL = "spring";
 * }
 *
 * wiring:
 * @ Mapper(componentModel = MapperConfig.COMPONENT_MODEL)
 * (space between @ and mapper is just to satisfy static analysis... anyways remove this comment when todo is done.)
 */
@Mapper(componentModel = "spring")
public interface OrganisationMapper {

    OrganisationDTO toOrganisationDTO(OrganisationEntity orgEnt);

    List<OrganisationDTO> toOrganisationDTOs(List<OrganisationEntity> orgEnts);

    // TODO: there is warning in idea console, code analysis or maven build saying
    // "Warning:(28, 10) Unmapped target property: projects"
    // this mean OrganisationEntity has field projects, which does not exists in dto
    // to solve this:
    // 1: add "ignore mapping" annotation here to tell mapstruct this field is not mapped
    // 2: add projects into dto, but then we have question is it list of project ids or project DTO:s
    // 3: I think we do not really have use case where we would need to fetch organisations with projects
    //    so this field could be removed from entity (and database)...
    //    it can be added if we end up having such a use case
    // DONE: removed projects field from entity altogether
    void valuesFromDTO(OrganisationDTO orgDTO, @MappingTarget OrganisationEntity organisationEntity);

}
