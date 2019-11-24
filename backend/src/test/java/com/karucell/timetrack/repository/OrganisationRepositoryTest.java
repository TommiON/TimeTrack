package com.karucell.timetrack.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.karucell.timetrack.repository.entity.OrganisationEntity;

@RunWith(SpringRunner.class)
@DataJpaTest
public class OrganisationRepositoryTest {

    @Autowired
    private OrganisationRepository organisationRepository;
    @Autowired
    private ProjectRepository projectRepository;

    // Testitesti: meneeköhän yhtään oikein?
    // jep, ihan oikeaan suuntaan on menossa, tosin tää on sama testi kuin
    // findById_WithNonExistingId_shouldReturnEmptyOptional
    @Test
    public void hae_organisaationimi_tyhjastaKannasta() {
        long id = 1;
        Optional<OrganisationEntity> entity = organisationRepository.findById(id);
        // String nimi = entity.getName();
        assertFalse(entity.isPresent());
    }

    @Test
    public void save_NonExistingEntity_shouldPersistGivenEntity() {
        // given
        OrganisationEntity entity = new OrganisationEntity(
                null,
                "anyValidName",
                "anyValidIdentifier",
                true
        );

        // when
        entity = organisationRepository.save(entity);

        // then
        assertNotNull("should return persisted entity", entity);
        assertNotNull("Id should be set after persist", entity.getId());
    }

    @Test
    public void findById_WithNonExistingId_shouldReturnEmptyOptional() {
        // given
        Long id = 987654321L;

        // when
        Optional<OrganisationEntity> entity = organisationRepository.findById(id);

        // then
        assertFalse("should return empty optional", entity.isPresent());
    }

    @Test
    public void findById_WithExistingId_shouldReturnEntity() {
        // given
        OrganisationEntity entity = new OrganisationEntity(
                null,
                "anyValidName",
                "anyValidIdentifier",
                true
        );
        // and
        OrganisationEntity savedEntity = organisationRepository.save(entity);
        // and
        Long id = savedEntity.getId();

        // when
        Optional<OrganisationEntity> foundEntity = organisationRepository.findById(id);

        // then
        assertTrue("should return filled optional", foundEntity.isPresent());
        // and
        assertEquals("found entity id should match argument", id, foundEntity.get().getId());
    }

    @Test
    public void findByIdWithAssociations_whenOrganisationHasOneProject_shouldReturnOrganisationWithProjects() {
        // given
        OrganisationEntity organisation = new OrganisationEntity(
                null,
                "anyValidName",
                "anyValidIdentifier",
                true
        );
        // and
//        ProjectEntity projectEntity = new ProjectEntity(
//                null,
//                "anyValidProjectName",
//                true,
//                organisation
//        );
        // and
        // organisation.setProjects(Collections.singletonList(projectEntity));

        // and
        OrganisationEntity savedOrganisation = organisationRepository.save(organisation);
//        ProjectEntity savedProject = projectRepository.save(projectEntity);

        // when
        Optional<OrganisationEntity> foundOrganisation = organisationRepository.findById(savedOrganisation.getId());

        // then
        assertTrue("should return filled optional", foundOrganisation.isPresent());
        // Marko: in repository tests, its good to verify id match
        foundOrganisation.ifPresent(org -> {
            // and
            assertEquals("found organisation id should match", savedOrganisation.getId(), org.getId());
        });
    }

}