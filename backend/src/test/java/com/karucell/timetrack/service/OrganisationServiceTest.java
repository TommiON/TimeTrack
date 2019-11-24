package com.karucell.timetrack.service;

import static com.karucell.timetrack.AssertionTools.assertViolationsContainMessage;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.util.List;
import java.util.Optional;

import javax.validation.ConstraintViolationException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.karucell.timetrack.controller.OrganisationDTO;
import com.karucell.timetrack.repository.entity.OrganisationEntity;
import com.karucell.timetrack.repository.OrganisationRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrganisationServiceTest {

    @Autowired
    private OrganisationService organisationService;

    @Autowired
    private OrganisationRepository organisationRepository;

    @Before
    public void setup() {
        // given - empty database
        organisationRepository.deleteAll();
    }

    @Test
    public void fetchOrganisations_DatabaseEmpty_ShouldReturnEmptyList() {
        // given - empty database

        // when
        List<OrganisationDTO> result = organisationService.fetchOrganisations();

        // then
        assertNotNull(result);
        assertEquals(0 , result.size());
    }

    @Test
    public void fetchOrganisations_NonEmptyDatabase_ShouldReturnListOfResults() {
        // given - empty database
        OrganisationEntity org = organisationRepository.save(
                new OrganisationEntity(null, "anyValidName", "anyValidYTJId", true)
        );

        // when
        List<OrganisationDTO> result = organisationService.fetchOrganisations();

        // then
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(org.getId(), result.get(0).getId());
    }

    @Test
    public void fetchOrganisation_DatabaseEmpty_ShouldReturnEmptyOptional() {
        // given - empty database
        Long orgId = 123456789L;

        // when
        Optional<OrganisationDTO> result = organisationService.fetchOrganisation(orgId);

        // then
        assertFalse(result.isPresent());
    }

    @Test
    public void fetchOrganisation_NonEmptyDatabase_ShouldReturnFilledOptional() {
        // given - empty database
        OrganisationEntity organisationEntity = organisationRepository.save(
                new OrganisationEntity(null, "anyValidName", "anyValidYTJId", true)
        );

        // when
        Optional<OrganisationDTO> result = organisationService.fetchOrganisation(organisationEntity.getId());

        // then
        assertTrue(result.isPresent());
        result.ifPresent(dto -> assertDTOEqualsEntityById(dto, organisationEntity));
    }

    @Test
    public void createNewOrganisation_WithValidData_ShouldSaveAndReturnNewOrganisation() {
        // given
        OrganisationDTO newOrg = new OrganisationDTO(
                null,
                "anyValidName",
                "anyValidYTJId",
                true
        );

        // when
        Optional<OrganisationDTO> created = organisationService.createNewOrganisation(newOrg);

        // then
        assertTrue(created.isPresent());
        created.ifPresent(organisationEntity -> {
            assertNotNull(organisationEntity.getId());
            assertEquals(newOrg.getName(), organisationEntity.getName());
            assertEquals(newOrg.getYtjIdentifier(), organisationEntity.getYtjIdentifier());
        });

    }

    @Test
    public void createNewOrganisation_WithValidData_ShouldSetActivateFlagToTrue() {
        // given
        OrganisationDTO newOrg = new OrganisationDTO(
                null,
                "anyValidName",
                "anyValidYTJId",
                false
        );

        // when
        Optional<OrganisationDTO> created = organisationService.createNewOrganisation(newOrg);

        // then
        assertTrue(created.isPresent());
        created.ifPresent(organisationEntity -> assertTrue(organisationEntity.isActive()));
    }

    @Test
    public void createNewOrganisation_WithInvalidData_ShouldThrow() {
        // given
        String invalidName = null;
        // and
        OrganisationDTO newOrg = new OrganisationDTO(
                null,
                invalidName,
                "anyValidYTJId",
                true
        );

        // when
        try {
            organisationService.createNewOrganisation(newOrg);
            fail("Should throw ConstraintViolationException");
        } catch (ConstraintViolationException e) {
            // then
            assertViolationsContainMessage(e.getConstraintViolations(), "name.isEmpty");
        } catch (Exception e) {
            fail("Should throw ConstraintViolationException");
        }
    }

    @Test
    public void updateOrganisation_WithValidData_ShouldSaveAndReturnUpdatedOrganisation() {
        // given
        OrganisationEntity existingOrg = organisationRepository.save(
                new OrganisationEntity(null, "Initial name", "Initial YTJ ID", true)
        );
        // and
        OrganisationDTO orgWithNewValues = new OrganisationDTO(
                existingOrg.getId(),
                "Updated name",
                "Updated YTJ ID",
                true
        );

        // when
        Optional<OrganisationDTO> updatedOrg = organisationService.updateOrganisation(orgWithNewValues);

        // then
        assertTrue(updatedOrg.isPresent());
        updatedOrg.ifPresent(organisationEntity -> assertEquals(orgWithNewValues, organisationEntity));
    }

    @Test
    public void updateOrganisation_WithInvalidValidData_ShouldThrow() {
        // given
        OrganisationEntity existingOrg = organisationRepository.save(
                new OrganisationEntity(null, "Initial name", "Initial YTJ ID", true)
        );
        // and
        OrganisationDTO orgWithNewValues = new OrganisationDTO(
                existingOrg.getId(),
                null,
                "Updated YTJ ID",
                true
        );

        // when
        try {
            organisationService.updateOrganisation(orgWithNewValues);
            fail("Should throw ConstraintViolationException");
        } catch (ConstraintViolationException e) {
            // then
            assertViolationsContainMessage(e.getConstraintViolations(), "name.isEmpty");
            // and - entity in DB should not be touched
            Optional<OrganisationEntity> entityInDB = organisationRepository.findById(existingOrg.getId());
            assertTrue(entityInDB.isPresent());
            entityInDB.ifPresent(organisationEntity -> assertEquals(existingOrg, organisationEntity));
        } catch (Exception e) {
            fail("Should throw ConstraintViolationException");
        }
    }

    @Test
    public void disableOrganisation_ShouldSetDisabledFlagOn() {
        // given
        OrganisationEntity existingOrg = organisationRepository.save(
                new OrganisationEntity(null, "Initial name", "Initial YTJ ID", true)
        );

        // when
        Optional<OrganisationEntity> disabledOrganisation = organisationService.disableOrganisation(existingOrg.getId());

        // then
        assertTrue(disabledOrganisation.isPresent());
        disabledOrganisation.ifPresent(organisationEntity -> assertFalse(organisationEntity.getActive()));
    }

    @Test
    public void fetchOrganisation_DisabledEntity_shouldReturnEmptyOptional() {
        // given
        OrganisationEntity disabledOrg = organisationRepository.save(
                new OrganisationEntity(null, "Initial name", "Initial YTJ ID", false)
        );

        // when
        Optional<OrganisationDTO> emptyResult = organisationService.fetchOrganisation(disabledOrg.getId());

        // then
        assertFalse(emptyResult.isPresent());
    }

    @Test
    public void fetchOrganisations_WithEnabledAndDisabledEntities_shouldReturnOnlyEnabled() {
        // given
        OrganisationEntity enabled1 = organisationRepository.save(
                new OrganisationEntity(null, "name1", "YTJ ID 1", true)
        );
        OrganisationEntity disabled2 = organisationRepository.save(
                new OrganisationEntity(null, "name2", "YTJ ID 2", false)
        );
        OrganisationEntity enabled3 = organisationRepository.save(
                new OrganisationEntity(null, "name3", "YTJ ID 3", true)
        );

        // when
        List<OrganisationDTO> result = organisationService.fetchOrganisations();

        // then
        assertEquals(2, result.size());
        assertTrue(result.stream().anyMatch(organisationDTO -> organisationDTO.getId().equals(enabled1.getId())));
        assertTrue(result.stream().noneMatch(organisationDTO -> organisationDTO.getId().equals(disabled2.getId())));
        assertTrue(result.stream().anyMatch(organisationDTO -> organisationDTO.getId().equals(enabled3.getId())));
    }

    private void assertDTOEqualsEntityById(OrganisationDTO dto, OrganisationEntity entity) {
        assertEquals("Id in DTO should match to id in Entity", dto.getId(), entity.getId());
    }

}