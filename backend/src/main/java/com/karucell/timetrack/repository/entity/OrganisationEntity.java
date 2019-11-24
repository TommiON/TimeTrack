package com.karucell.timetrack.repository.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@Data
@EqualsAndHashCode //(exclude = {"projects"}) // exclude all relations, to avoid persistence exceptions
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "organisation")
public class OrganisationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty(message = "name.isEmpty")
    @Size(min = 3, message = "name.isTooShort")
    @Size(max = 80, message = "name.isTooLong")
    private String name;

    @NotEmpty(message = "ytjIdentifier.isEmpty")
    @Size(min = 3, message = "ytjIdentifier.isTooShort")
    @Size(max = 80, message = "ytjIdentifier.isTooLong")
    private String ytjIdentifier;

    @NotNull(message = "active.isNull")
//    @AssertTrue(message = "active.isDisabled")
    private Boolean active;

//    @OneToMany(mappedBy = "organisation", orphanRemoval = false)
//    private List<ProjectEntity> projects;
//    List<UserEntity> users;

}
