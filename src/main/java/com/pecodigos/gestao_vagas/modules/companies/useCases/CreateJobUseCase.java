package com.pecodigos.gestao_vagas.modules.companies.useCases;

import com.pecodigos.gestao_vagas.modules.companies.entities.JobEntity;
import com.pecodigos.gestao_vagas.modules.companies.repositories.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateJobUseCase {

    @Autowired
    private JobRepository jobRepository;

    public JobEntity execute(JobEntity jobEntity) {
        return this.jobRepository.save(jobEntity);
    }
}
