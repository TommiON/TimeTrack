package com.karucell.timetrack.repository.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="Project")
public class ProjectEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private boolean active;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "organisation_id")
//    private OrganisationEntity organisation;

//    private List<UserEntity> owners;
//    private List<UserEntity> team;
//    private List<JobEntity> jobEntries;

}
