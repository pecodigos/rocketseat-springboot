package com.pecodigos.gestao_vagas.modules.candidates.useCases;

import com.pecodigos.gestao_vagas.exceptions.JobNotFoundException;
import com.pecodigos.gestao_vagas.exceptions.UserNotFoundException;
import com.pecodigos.gestao_vagas.modules.candidates.repositories.ApplyJobRepository;
import com.pecodigos.gestao_vagas.modules.candidates.repositories.CandidateRepository;
import com.pecodigos.gestao_vagas.modules.company.repositories.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ApplyJobCandidateUseCase {

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private ApplyJobRepository applyJobRepository;

    // ID de candidato
    // ID da vaga
    public void execute(UUID candidateId, UUID jobId) {
        // Validar se candidato existe
        this.candidateRepository.findById(candidateId).orElseThrow(UserNotFoundException::new);

        // Validar se a vaga existe
        this.jobRepository.findById(jobId).orElseThrow(JobNotFoundException::new);


        // Inscrever candidato na vaga
    }

}
