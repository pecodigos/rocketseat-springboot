package com.pecodigos.gestao_vagas.modules.candidates.useCases;

import com.pecodigos.gestao_vagas.exceptions.JobNotFoundException;
import com.pecodigos.gestao_vagas.exceptions.UserNotFoundException;
import com.pecodigos.gestao_vagas.modules.candidates.entities.ApplyJobEntity;
import com.pecodigos.gestao_vagas.modules.candidates.entities.CandidateEntity;
import com.pecodigos.gestao_vagas.modules.candidates.repositories.ApplyJobRepository;
import com.pecodigos.gestao_vagas.modules.candidates.repositories.CandidateRepository;
import com.pecodigos.gestao_vagas.modules.company.entities.JobEntity;
import com.pecodigos.gestao_vagas.modules.company.repositories.JobRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ApplyJobCandidateUseCaseTest {

    @InjectMocks
    private ApplyJobCandidateUseCase applyJobCandidateUseCase;

    @Mock
    private CandidateRepository candidateRepository;

    @Mock
    private JobRepository jobRepository;

    @Mock
    private ApplyJobRepository applyJobRepository;


    @Test
    @DisplayName("Should not be able to apply job if candidate's not found")
    public void shouldNotBeAbleToApplyJobIfCandidateNotFound() {
        try{
            applyJobCandidateUseCase.execute(null, null);
        } catch (Exception e) {
            assertThat(e).isInstanceOf(UserNotFoundException.class);
        }
    }

    @Test
    @DisplayName("Should not be able to apply job if job's not found")
    public void shouldNotBeAbleToApplyJobIfJobNotFound() {
        var candidateId = UUID.randomUUID();
        var candidate = new CandidateEntity();
        candidate.setId(candidateId);

        when(candidateRepository.findById(candidateId)).thenReturn(Optional.of(candidate));

        try{
            applyJobCandidateUseCase.execute(candidateId, null);
        } catch (Exception e) {
            assertThat(e).isInstanceOf(JobNotFoundException.class);
        }
    }

    @Test
    @DisplayName("Should be able to create a new apply job")
    public void shouldBeAbleToCreateANewApplyJob() {

        var candidateId = UUID.randomUUID();
        var jobId = UUID.randomUUID();
        var applyJob = ApplyJobEntity.builder().candidateId(candidateId).jobId(jobId).build();

        var applyJobCreate = ApplyJobEntity.builder().id(UUID.randomUUID()).build();


        when(candidateRepository.findById(candidateId)).thenReturn(Optional.of(new CandidateEntity()));
        when(jobRepository.findById(jobId)).thenReturn(Optional.of(new JobEntity()));
        when(applyJobRepository.save(applyJob)).thenReturn(applyJobCreate);

        var result = applyJobCandidateUseCase.execute(candidateId, jobId);
        assertThat(result).hasFieldOrProperty("id");
        assertNotNull(result.getId());

    }
}
