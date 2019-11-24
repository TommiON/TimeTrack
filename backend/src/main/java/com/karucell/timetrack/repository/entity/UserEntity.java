package com.karucell.timetrack.repository.entity;

import java.util.Objects;

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
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode

@Entity
@Table(name = "user")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty(message = "name.isEmpty")
    @Size(min = 3, message = "name.isTooShort")
    @Size(max = 80, message = "name.isTooLong")
    private String name;

    @NotNull(message = "active.isNull")
    private boolean admin;


//    private List<ProjectEntity> projects;
//    private List<JobEntity> jobEntries;

//    public List<ProjectEntity> getProjects() {
//        return projects;
//    }

//    public void setProjects(List<ProjectEntity> projects) {
//        this.projects = projects;
//    }

//    public List<JobEntity> getJobEntries() {
//        return jobEntries;
//    }

//    public void setJobEntries(List<JobEntity> jobEntries) {
//        this.jobEntries = jobEntries;
//    }

}
