package com.pecodigos.gestao_vagas.modules.companies.repositories;

import com.pecodigos.gestao_vagas.modules.companies.entities.JobEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface JobRepository extends JpaRepository<JobEntity, UUID> {

    // "contains - LIKE"
    // SELECT * FROM job WHERE description 'LIKE'
    List<JobEntity> findByDescriptionContainingIgnoreCase(String filter);
}
