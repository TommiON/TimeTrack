package com.karucell.timetrack.repository.entity;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode

@Entity
@Table(name = "timecard")
public class TimecardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

//    private ProjectEntity attachedProject;



//    public ProjectEntity getAttachedProject() {
//        return attachedProject;
//    }
//
//    public void setAttachedProject(ProjectEntity attachedProject) {
//        this.attachedProject = attachedProject;
//    }

}
