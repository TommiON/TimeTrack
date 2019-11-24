package com.karucell.timetrack.repository;

import com.karucell.timetrack.repository.entity.JobEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobRepository extends JpaRepository<JobEntity, Long>{
}
