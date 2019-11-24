package com.karucell.timetrack.repository;

import com.karucell.timetrack.repository.entity.TimecardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TimecardRepository extends JpaRepository<TimecardEntity, Long>{
}