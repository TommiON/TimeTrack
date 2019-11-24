package com.karucell.timetrack.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class OrganisationDTO {
    /**
     * TODO: need to figure out how to match client data structure to existing database row...
     *       its a bad practice to deliver id directly from database table to client.
     * TODO: also we need to add version field into DTO and Entity, so that when updating we can match
     *       client has same version of data than they are updating, if not, then someone else has updated
     *       database between client fetch/update.
     */
    Long id;
    String name;
    String ytjIdentifier;
    boolean active;
}
