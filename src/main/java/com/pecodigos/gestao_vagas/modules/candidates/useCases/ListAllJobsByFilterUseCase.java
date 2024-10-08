package com.pecodigos.gestao_vagas.modules.candidates.useCases;

import com.pecodigos.gestao_vagas.modules.companies.entities.JobEntity;
import com.pecodigos.gestao_vagas.modules.companies.repositories.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListAllJobsByFilterUseCase {

    @Autowired
    private JobRepository jobRepository;

    public List<JobEntity> execute(String filter) {
        return this.jobRepository.findByDescriptionContainingIgnoreCase(filter);
    }
}
