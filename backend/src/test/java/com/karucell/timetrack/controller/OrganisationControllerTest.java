package com.karucell.timetrack.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.karucell.timetrack.service.OrganisationService;

@RunWith(SpringRunner.class)
@WebMvcTest(OrganisationController.class)
public class OrganisationControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private OrganisationService organisationServiceMock;

    /**
     * Marko: architecture is:
     *        controller => service => repository
     *        meaning, when testing controller, its enough to mock service, since its controllers collaborator.
     *        repository is not needed, cos mock does not call anything, it only return what we say it should return.
     *        If we were testing whole stack, then we would initialize some data using repository.
     *        => so organisationRepository can be removed from this test
     *
     *        Also, we do not need organisationMapper in this test, cos its mapping entities into dto:s.
     *        Yet more, we are not dealing with entities in this layer, so we are not even creating entities.
     *        But we are creating DTO:s - those that service return
     */

    @Autowired
    private ObjectMapper objectMapper;

    /**
     * example test:
     * there are many ways to write tests, but writing readable tests should be first priority
     * personally I find gherkin notation the most reader friendly way of expressing intent, so I suggest it here also
     *
     * Good test should have following blocks:
     * setup
     * exercise
     * verifications
     *
     * At least separate those with empty line, but if using gherkin notation, comment blocks with
     * // given
     * // when
     * // then
     *
     */
    @Test
    public void actionOrFunctionWhichIsTested_InWhatKindOfCondition_WhatResultIsExpected() {
        // given
        int value = 1;
        // and
        int expectedResultOfAdding = 2;

        // when
        int result = value + 1;

        // then
        assertEquals(expectedResultOfAdding, result);
    }

    @Test
    public void sameTestAsAbove_theWayPeopleCommonlyWriteTests() {
        assertEquals(2, 1+1);
        // look how many lines we saved! we must be proud of our selfs cos we are not over populating git!
        // next guy who come must think we are extremely clever since we expressed so much in one line.
        // ... or something like that must go on in minds of people who are in business of saving lines
        //     instead of trying to communicate intent.
    }

    @Test
    public void whenGetOrganisations_thenRepliesCorrectly() throws Exception {
        // given
        OrganisationDTO dto1 = new OrganisationDTO(123L, "AnyValidName", "AnyValidYTJ", true);
        OrganisationDTO dto2 = new OrganisationDTO(456L, "OtherValidName", "OtherValidYTJ", true);
        // and
        List<OrganisationDTO> listOfOrganisations = Arrays.asList(dto1, dto2);
        // and
        when(organisationServiceMock.fetchOrganisations()).thenReturn(listOfOrganisations);
        // and
        String expectedJSON = objectMapper.writeValueAsString(listOfOrganisations);

        // when
        mvc.perform(get("/organisations")
                .contentType(MediaType.APPLICATION_JSON))
                // then
                .andExpect(status().isOk())
                .andExpect(content().string(expectedJSON));

        // and
        verify(organisationServiceMock, times(1)).fetchOrganisations();
    }

    @Test
    public void whenGetSingleOrganisation_thenRepliesCorrectly() throws Exception {
        // given
        OrganisationDTO dto = new OrganisationDTO(456L, "OtherValidName", "OtherValidYTJ", true);
        // and
        when(organisationServiceMock.fetchOrganisation(456L)).thenReturn(Optional.of(dto));
        // and
        String expectedJSON = objectMapper.writeValueAsString(dto);

        // when
        mvc.perform(get("/organisations/{id}", 456L)
                .contentType(MediaType.APPLICATION_JSON))

                // then
                .andExpect(status().isOk())
                .andExpect(content().string(expectedJSON));

        // and
        verify(organisationServiceMock, times(1)).fetchOrganisation(456L);
    }

    @Test
    public void whenPostNewOrganisation_thenRepliesCorrectly() throws Exception {
        // given
        OrganisationDTO inputDTO = new OrganisationDTO(123L, "AnyValidName", "AnyValidYTJ", true);
        // and
        String inputJSON = objectMapper.writeValueAsString(inputDTO);
        // and
        when(organisationServiceMock.createNewOrganisation(inputDTO)).thenReturn(Optional.of(inputDTO));
        // and
        String expectedJSON = objectMapper.writeValueAsString(inputDTO);

        // when
        mvc.perform(post("/organisations")
                .contentType(MediaType.APPLICATION_JSON)
                .content(inputJSON))
                // then
                .andExpect(content().string(expectedJSON))
                .andExpect(status().isOk());

        // and
        verify(organisationServiceMock, times(1)).createNewOrganisation(inputDTO);
    }

    @Test
    public void whenPutUpdatedOrganisation_thenRepliesCorrectly() throws Exception {
        // given
        OrganisationDTO inputTdo = new OrganisationDTO(123L, "AnyValidName", "AnyValidYTJ", true);
        // and
        String testJson = objectMapper.writeValueAsString(inputTdo);
        // and
        when(organisationServiceMock.updateOrganisation(inputTdo)).thenReturn(Optional.of(inputTdo));
        // and
        String expectedJson = objectMapper.writeValueAsString(inputTdo);

        // when
        mvc.perform(put("/organisations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(testJson))
            // then
            .andExpect(status().isOk())
                .andExpect(content().string(expectedJson));

        // and
        verify(organisationServiceMock, times(1)).updateOrganisation(inputTdo);
    }

}
