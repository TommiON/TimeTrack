package com.karucell.timetrack.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.karucell.timetrack.repository.entity.OrganisationEntity;

@Repository
public interface OrganisationRepository extends JpaRepository<OrganisationEntity, Long> {

    // Overriding findById to filter out disabled entities
    @Query("SELECT o FROM OrganisationEntity o WHERE o.id = (:id) AND o.active = true")
    Optional<OrganisationEntity> findById(@Param("id") Long id);

    // Overriding findAll to filter out disabled entities
    @Query("SELECT o FROM OrganisationEntity o WHERE o.active = true")
    List<OrganisationEntity> findAll();


//    @Query("SELECT o FROM OrganisationEntity o JOIN FETCH o.projects WHERE o.id = (:id)")
//    OrganisationEntity findByIdWithAssociations(@Param("id") Long id);
}
