package com.karucell.timetrack.repository.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
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
@Table(name = "job")
public class JobEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    //private UserEntity doer;

    @NotEmpty(message = "description.isEmpty")
    @Size(min = 3, message = "description.isTooShort")
    @Size(max = 80, message = "description.isTooLong")
    private String description;

    // Toimiiko Lombok ongelmitta myös, jos muuttuja on jokin muu kuin String? Varmaan toimii?
    // Ja generoidaanko näille joka tapauksessa metodit, vaikka ei olisi mitään annotaatioita?
    // => jep, toimii kaikille tietotyypeille... @Data tuolla luokan alussa kertoo että kentille
    //    generoidaan getter/setterit
    private Date start;
    private Date end;

//    public UserEntity getDoer() {
//        return doer;
//    }

//    public void setDoer(UserEntity doer) {
//        this.doer = doer;
//    }

}
